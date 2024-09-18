package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestScript {
    public static void main(String[] args) {
        try {
            // Thực thi lệnh hoặc script với đường dẫn tuyệt đối
            String folder = "s3data";

            String userHome = System.getProperty("user.home");

            Runtime.getRuntime().exec(String.format("mkdir %s/Documents/%s", userHome, folder));

            String command = String.format("mc cp --recursive miniolocal/idp-bic/ %s/Documents/%s", userHome, folder);

            Process process = Runtime.getRuntime().exec(command);

            // Đọc output của lệnh
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Đọc error output (quan trọng để biết nếu có lỗi xảy ra)
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
            }

            // Chờ quá trình hoàn thành
            int exitCode = process.waitFor();
            System.out.println("Exit code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
