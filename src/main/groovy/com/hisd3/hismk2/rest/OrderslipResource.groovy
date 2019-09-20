package com.hisd3.hismk2.rest

import com.hisd3.hismk2.domain.ancillary.DiagnosticResult
import com.hisd3.hismk2.domain.ancillary.Orderslip
import com.hisd3.hismk2.repository.ancillary.DiagnosticsResultRepository
import com.hisd3.hismk2.repository.ancillary.OrderslipRepository
import groovy.transform.TypeChecked
import javassist.bytecode.ByteArray
import jcifs.smb.NtlmPasswordAuthentication
import jcifs.smb.SmbFile
import jcifs.smb.SmbFileInputStream
import jcifs.smb.SmbFileOutputStream
import org.apache.commons.io.FilenameUtils
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartRequest

@TypeChecked
@RestController
class OrderslipResource {
	
	@Autowired
	private OrderslipRepository orderslipRepository
	
	@Autowired
	DiagnosticsResultRepository diagnosticsResultRepository
	
	@RequestMapping(method = [RequestMethod.GET], value = "/api/orderSlips/getImageResults/{id}")
	ResponseEntity<ByteArray> getImageResults(@PathVariable(value = "id") String id) {
		
		HttpHeaders responseHeaders = new HttpHeaders()
		
		DiagnosticResult resultImage = diagnosticsResultRepository.findById(UUID.fromString(id)).get()
		if (resultImage != null) {
			if (resultImage.url_path != null) {
				NtlmPasswordAuthentication ntlmPasswordAuthentication = new NtlmPasswordAuthentication(null, "hisd3", "xsXY4;")
				SmbFile attachementfile = new SmbFile(resultImage.url_path, ntlmPasswordAuthentication)
				
				SmbFileInputStream inFile = new SmbFileInputStream(attachementfile)
				BufferedInputStream bMess = new BufferedInputStream(inFile)
				byte[] data = bMess.bytes
				//bMess.close()
				//val filename = inFile.toString()
				LinkedMultiValueMap params = new LinkedMultiValueMap<String, String>()
				params.add("Content-Disposition", "inline;filename=" + resultImage.file_name)
				params.add("Content-Header", resultImage.file_name)
				//val mimeType = URLConnection.guessContentTypeFromName(filename)
				//var mime = Tika().detect(data)
				params.add("Content-Type", resultImage.mimetype)
				return new ResponseEntity(data, params, HttpStatus.OK)
			}
		} else {
			
			return new ResponseEntity(responseHeaders, HttpStatus.NOT_FOUND)
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/orderSlips/addresultsimages")
	ResponseEntity<String> addresultsimages(@RequestParam String id, MultipartRequest request) {
		
		Orderslip orderSlip = orderslipRepository.findById(UUID.fromString(id)).get()
		
		def attachement = request.getFiles("file")
		
		try {
			attachement.forEach { file ->
				MultipartFile f = file
				DiagnosticResult uploadedResult = new DiagnosticResult()
//                resutSlipResource.data = file.bytes.toTypedArray()
				uploadedResult.file_name = f.originalFilename
				uploadedResult.mimetype = f.contentType
				uploadedResult.service = orderSlip.service
				uploadedResult.orderSlip = orderSlip
				diagnosticsResultRepository.save(uploadedResult)
				
				try {
					/*** ready for NAS***/
					String origin = f.resource.filename
					String extension = FilenameUtils.getExtension(origin)
					String idfname = uploadedResult.id.toString() + "." + extension
					byte[] byteData = f.getBytes()
					try {
						
						uploadedResult.url_path = resultWitteronSmb(orderSlip, byteData, idfname)
						diagnosticsResultRepository.save(uploadedResult)
					} catch (Exception e) {
						e.printStackTrace()
						return new ResponseEntity<>(
								"Error uploading Files",
								HttpStatus.BAD_REQUEST)
					}
					
				} catch (Exception e) {
					e.printStackTrace()
				}
			}
			
			return new ResponseEntity<>(
					"Success Uploading Files",
					HttpStatus.OK)
			
		} catch (Exception e) {
			
			e.printStackTrace()
			return new ResponseEntity<>(
					"Error uploading Files",
					HttpStatus.BAD_REQUEST)
		}
	}
	
	String resultWitteronSmb(Orderslip orderSlip, byte[] byteData, String fname) {
		
		// var hospInfo = hospitalInfoRepository.findAll().firstOrNull()
		String tofile = null
		try {
			NtlmPasswordAuthentication ntlmPasswordAuthentication = new NtlmPasswordAuthentication(null, "hisd3", "xsXY4;")
//			def shared = "smb://127.0.0.1/Shared/"
			def shared = "smb://172.16.12.30/Diagnostics/HISMKII/"
			SmbFile directory = new SmbFile(shared, ntlmPasswordAuthentication)

//          val path = shared + orderSlip.patientID +"/"+orderSlip?.serviceFee?.department + "/" + orderSlip.serviceFee?.category + "/"

//          var pFolder = if (hospInfo?.live_deployment!!) orderSlip.pdsc?.patient?.patientNo.toString() + "/" else "DEMO" + orderSlip.pdsc?.patient?.patientNo.toString() + "/"
			
			String pFolder = orderSlip.parentCase.patient.patientNo.toString() + "/"
			String dFolder = StringUtils.trim(orderSlip.service.serviceName).replace(" ", "") + "/"
			String caseFolder = StringUtils.trim(orderSlip.parentCase.caseNo.toString()) + "/"
			String finalName = StringUtils.trim(fname)
			
			SmbFile sFile = new SmbFile(shared + pFolder, ntlmPasswordAuthentication)
			
			if (folderCreator(sFile)) {
				SmbFile sFile1 = new SmbFile(shared + pFolder + caseFolder, ntlmPasswordAuthentication)
				if (folderCreator(sFile1)) {
					
					SmbFile sFile2 = new SmbFile(shared + pFolder + caseFolder + dFolder, ntlmPasswordAuthentication)
					
					folderCreator(sFile2)
				}
			}
			
			tofile = shared + pFolder + caseFolder + dFolder + finalName
			SmbFile sFileFinal = new SmbFile(tofile, ntlmPasswordAuthentication)
			
			SmbFileOutputStream sfos = new SmbFileOutputStream(sFileFinal)
			sfos.write(byteData)
			sfos.flush()
			sfos.close()
		} catch (Exception e) {
			e.printStackTrace()
		}
		
		return tofile
	}
	
	Boolean folderCreator(SmbFile smbFile) {
		
		try {
			if (!smbFile.exists()) {
				smbFile.mkdir()
			}
		} catch (IOException e) {
			//throw IllegalArgumentException(e.message)
			e.printStackTrace()
		}
		return true
	}
	
}
