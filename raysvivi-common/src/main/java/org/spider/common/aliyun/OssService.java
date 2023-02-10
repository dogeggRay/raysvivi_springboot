package org.spider.common.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.spider.common.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
public class OssService{
    @Autowired
    private OssProperties ossProperties;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    @PostConstruct
    public void init() {
        this.endpoint = this.ossProperties.getEndpoint();
        this.accessKeyId = this.ossProperties.getAccessKeyId();
        this.accessKeySecret = this.ossProperties.getAccessKeySecret();
        this.bucketName = this.ossProperties.getBucketName();
    }

    public String uploadImage(MultipartFile file,String fileDir) throws Exception {
        OSS ossClient = null;
        log.info("start uploading image");
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();
            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            String fileName = fileDir+"/"+DateTimeUtil.getCurrentDate()+"/"
                    + UUID.randomUUID().toString().replace("-", "")
                    + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            // 参数1：保存文件的oss中的桶名； 参数2：保存上传的文件在桶内的路径和文件名； 参数3：上传的文件流
            ossClient.putObject(bucketName, fileName, inputStream);


            inputStream.close();
            // 这个图片路径需要手动拼接
            String path = "https://" + bucketName + "." +endpoint + "/" + fileName;
            return path;
        } catch (Exception e) {
            // 将编译时的异常转为运行时的异常：异常抛出后会被全局异常处理器处理
            // throw new RuntimeException();
            // 抛出自定义异常
            throw new Exception("图片上传失败");
        }finally{
            log.error("upload file failed");
            // 关闭OSSClient。
            if(ossClient!=null){
                ossClient.shutdown();
            }

        }
    }
}
