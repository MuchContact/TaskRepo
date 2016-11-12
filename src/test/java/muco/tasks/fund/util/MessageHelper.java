package muco.tasks.fund.util;

import muco.tasks.fund.Fund;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageHelper {
    public static boolean generateMessage(Fund fund, String crawlingTime, String to, String msgStorePath) {
        String content = String.format("%s\n------\n爬取时间:%s", fund.string(), crawlingTime);
        String template = String.format("{\"to\": \"%s\", \"msg\":\"%s\"}", to, content);
        Path targetPath = Paths.get(msgStorePath);

        try {
            if (!Files.exists(targetPath)) {
                Files.createDirectories(targetPath);
            }
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String newFile = String.format("%s/%s_%s.json", msgStorePath, to, timestamp);
            Path path = Paths.get(newFile);
            Files.write(path, template.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
