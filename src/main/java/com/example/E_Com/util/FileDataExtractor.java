package com.example.E_com.util;

import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

@Service
public class FileDataExtractor {
    public byte[] blobToByteArray(Blob blob) throws SQLException, IOException {
        if(blob == null){
            return new byte[0];
        }

        try(InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            byte[] buffer = new byte[4096];
            int byteRead;

            while((byteRead=inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,byteRead);
            }

            return outputStream.toByteArray();

        }
    }

    public String extractActualFileName(InputStreamReader streamReader){
        try{
            StringBuffer stringBuffer = new StringBuffer();
            String temp;
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            while((temp = bufferedReader.readLine()) != null){
                stringBuffer.append(temp);
            }
            return stringBuffer.toString();

        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
