package com.hisd3.hismk2.rest

import com.hisd3.hismk2.domain.ancillary.DiagnosticResult
import com.hisd3.hismk2.domain.ancillary.Orderslip
import com.hisd3.hismk2.repository.ancillary.DiagnosticsResultRepository
import com.hisd3.hismk2.repository.ancillary.OrderslipRepository
import javassist.bytecode.ByteArray
import org.apache.commons.io.FilenameUtils
import jcifs.smb.NtlmPasswordAuthentication
import org.apache.commons.lang3.StringUtils
import jcifs.smb.SmbFile
import jcifs.smb.SmbFileOutputStream
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartRequest

@RestController
class OrderslipResource {

    @Autowired
    private OrderslipRepository orderslipRepository

    @Autowired
    DiagnosticsResultRepository diagnosticsResultRepository

    @RequestMapping(method = RequestMethod.POST, value = "/api/orderSlips/addresultsimages")
    ResponseEntity<HttpStatus> addresultsimages(@RequestParam String id, MultipartRequest request ){

        Orderslip orderSlip = orderslipRepository.findById(UUID.fromString(id)).get()

        def  attachement = request.getFiles("file")

        try {
            attachement.forEach { file ->
                DiagnosticResult uploadedResult = new DiagnosticResult()
//                resutSlipResource.data = file.bytes.toTypedArray()

                uploadedResult.service = orderSlip.service
                uploadedResult.file_name = file.originalFilename
                uploadedResult.mimetype = file.contentType
                uploadedResult.orderslip = orderSlip
                diagnosticsResultRepository.save(uploadedResult)

                try {
                    /*** ready for NAS***/

                        String extension = FilenameUtils.getExtension(file.originalFilename)
                        String idfname = uploadedResult.id.toString() + "." + extension
                        ByteArray byteData = file.bytes

                        uploadedResult.url_path = resultWitteronSmb(orderSlip, byteData, idfname)
                        diagnosticsResultRepository.save(uploadedResult)

                }catch (Exception e){
                    e.printStackTrace()
                }
            }

            return (HttpStatus.CREATED)

        } catch ( Exception e ) {

            return (HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    String resultWitteronSmb(Orderslip orderSlip, ByteArray file, String fname){

       // var hospInfo = hospitalInfoRepository.findAll().firstOrNull()
        String tofile = null
        try {
            NtlmPasswordAuthentication ntlmPasswordAuthentication = new NtlmPasswordAuthentication(null, "hisd3", "xsXY4;")
            def shared = "smb://172.16.12.30/Diagnostics/HISMKII/"
            SmbFile directory = new SmbFile(shared, ntlmPasswordAuthentication)
            directory.isDirectory.let {
                directory.listFiles().forEach {
                    if (it.isFile) {
                        println("file: " + it.name.toString())
                    }
                }
            }

//          val path = shared + orderSlip.patientID +"/"+orderSlip?.serviceFee?.department + "/" + orderSlip.serviceFee?.category + "/"

//          var pFolder = if (hospInfo?.live_deployment!!) orderSlip.pdsc?.patient?.patientNo.toString() + "/" else "DEMO" + orderSlip.pdsc?.patient?.patientNo.toString() + "/"

            String pFolder =  orderSlip.parentCase.patient.patientNo.toString() + "/"
            String dFolder = StringUtils.trim(orderSlip.service.serviceName).replace(" ", "") + "/"
            String caseFolder = StringUtils.trim(orderSlip.parentCase.caseNo.toString()) + "/"
            String finalName = StringUtils.trim(fname)

            SmbFile sFile = new SmbFile(shared + pFolder, ntlmPasswordAuthentication)

            if(folderCreator(sFile)){
                    SmbFile  sFile1 = new SmbFile(shared + pFolder + caseFolder, ntlmPasswordAuthentication)
                if(folderCreator(sFile1)){

                    SmbFile sFile2 = new SmbFile(shared + pFolder + caseFolder + dFolder, ntlmPasswordAuthentication)

                    folderCreator(sFile2)
                }
            }


            tofile = shared + pFolder + caseFolder + dFolder + finalName
            SmbFile sFileFinal = new SmbFile(tofile, ntlmPasswordAuthentication)

            SmbFileOutputStream sfos = new SmbFileOutputStream(sFileFinal)
            sfos.write(file)
            sfos.flush()
            sfos.close()
        } catch (Exception e ) {
            e.printStackTrace()
        }

        return tofile
    }

    Boolean folderCreator(SmbFile smbFile) {

        try {
            if (!smbFile.exists()) {
                smbFile.mkdir()
            }
        } catch (IOException e ) {
            //throw IllegalArgumentException(e.message)
            e.printStackTrace()
        }
        return true
    }

}
