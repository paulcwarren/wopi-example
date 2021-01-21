package com.example.wopi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class WopiExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WopiExampleApplication.class, args);
	}

    @RestController
    public static class WopiController {

        @RequestMapping(method=RequestMethod.GET, value="/wopi/files/{fileId}", produces="application/json")
        public String wopiFile(HttpServletRequest request, HttpServletResponse response) {
            return
                    "{" +
                    "\"BaseFileName\":\"helloworld.odt\"," +
                    "\"OwnerId\":\"me\"," +
                    "\"Size\":4445," +
                    "\"UserId\":\"me\"," +
                    "\"Version\":\"1\"," +
                    "\"UserCanWrite\":true," +
                    "\"ReadOnly\":false," +
                    "\"SupportsLocks\":false," +
                    "\"SupportsUpdate\":true," +
                    "\"UserCanNotWriteRelative\":true," +
                    "\"UserFriendlyName\":\"some-name\"" +
            "}";
        }

        @RequestMapping(method=RequestMethod.GET, value="/wopi/files/{fileId}/contents")
        public byte[] getFileContent() throws FileNotFoundException, IOException {
            return IOUtils.toByteArray(new FileInputStream("/Users/warrenpa/Downloads/helloworld.odt"));
        }

        @RequestMapping(method=RequestMethod.POST, value="/wopi/files/{fileId}/contents")
        public void updateFileContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setStatus(200);
            response.flushBuffer();
        }
    }
}
