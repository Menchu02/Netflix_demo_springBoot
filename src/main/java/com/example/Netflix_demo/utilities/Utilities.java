package com.example.Netflix_demo.utilities;
import java.sql.Timestamp;
import java.util.UUID;

import com.example.Netflix_demo.model.ActivityLog;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
public class Utilities {
        public static ActivityLog createLog(HttpServletRequest request, String operationMethod, String domain,
                                            String status, String endpoint, String restMethod){



            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String ip= request.getRemoteAddr();
            ActivityLog activityLog = new ActivityLog();

            String uniqueID = UUID.randomUUID().toString();

            activityLog.setOperationMethod(operationMethod);
            activityLog.setDomain(domain);
            activityLog.setId(uniqueID);
            activityLog.setIp(ip);
            activityLog.setTime(timestamp);
            activityLog.setUser("anonymous");
            activityLog.setStatus(status);
            activityLog.setEndpoint(endpoint);
            //activityLog.setVersion("1.0");
            activityLog.setRestMethod(restMethod);

            return activityLog;

        }

        public static HttpHeaders createHeader(ActivityLog activityLog){
            HttpHeaders headers = new HttpHeaders();

            headers.add("operation", activityLog.getOperationMethod());
            headers.add("version", activityLog.getVersion());
            headers.add("domain", activityLog.getDomain());
            headers.add("status", activityLog.getStatus());
            headers.add("timestamp", activityLog.getTime().toString());
            headers.add("id", activityLog.getId());
            headers.add("user", activityLog.getUser());
            headers.add("endpoint", activityLog.getEndpoint());
            headers.add("ip", activityLog.getIp());
            headers.add("restMethod", activityLog.getRestMethod());
            return  headers;

        }

}
