package com.umbrella.demoSpringBoot.Utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Base64;
import java.util.Optional;

import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereMetaData;

@Service
public class SaveFileGridFs implements FileUtils {

    private final GridFsTemplate gridFsTemplate;

    public SaveFileGridFs(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    private static Query getFileQuery(String id) {
        return Query.query(whereMetaData("fileId").is(id));
    }

    private static Query getFilenameQuery(String name) {
        return Query.query(whereFilename().is(name));
    }

    @Override
    public HttpEntity<byte[]> saveFile(MultipartFile file, String id) {
        try {
            Optional<GridFSFile> existing = maybeLoadFile(id);
            existing.ifPresent(gridFSFile -> gridFsTemplate.delete(getFilenameQuery(gridFSFile.getFilename())));
            DBObject metaData = new BasicDBObject();
            metaData.put("fileId", id);
            gridFsTemplate.store(file.getInputStream(), id, file.getContentType(), metaData);
            String resp = "<script>window.location = '/';</script>";
            return new HttpEntity<>(resp.getBytes());
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public InputStreamResource getFileUrl(String id) {
        try {
            InputStream inputStream;
            Optional<GridFSFile> optionalCreated = maybeLoadFile(id);
            if (optionalCreated.isPresent()) {
                GridFSFile created = optionalCreated.get();
                inputStream = gridFsTemplate.getResource(created.getFilename()).getInputStream();
                return  new InputStreamResource(inputStream);
            } else {
                throw new RuntimeException("file not found");
            }
        } catch (IOException e) {
            throw new RuntimeException("file not found");
        }
    }

    @Override
    public byte[] getFileURL(String id) {
        try {
            byte[] data;
            Optional<GridFSFile> optionalCreated = maybeLoadFile(id);
            if (optionalCreated.isPresent()) {
                GridFSFile created = optionalCreated.get();
                InputStream inputStream = gridFsTemplate.getResource(created.getFilename()).getInputStream();
                data = IOUtils.toByteArray(inputStream);
                return data;
            } else {
                throw  new RuntimeException("File not found");
            }
        } catch (IOException e) {
            throw  new RuntimeException("File not found");
        }
    }

    @Override
    public String getFileBase64Encoded(String id) {
        try {
            InputStream inputStream;
            Optional<GridFSFile> optionalCreated = maybeLoadFile(id);
            if (optionalCreated.isPresent()) {
                GridFSFile created = optionalCreated.get();
                inputStream = gridFsTemplate.getResource(created.getFilename()).getInputStream();
                byte[] bytes = IOUtils.toByteArray(inputStream);
                return Base64.getEncoder().encodeToString(bytes);
            } else {
                throw new RuntimeException("file not found");
            }
        } catch (IOException e) {
            throw new RuntimeException("file not found");
        }
    }

    public Optional<GridFSFile> maybeLoadFile(String id) {
        GridFSFile file = gridFsTemplate.findOne(getFileQuery(id));
        return Optional.ofNullable(file);
    }
}
