package com.travelbook.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;

/**
 * AppApplication
 * @since 1.0
 * @version 1.0
 * @author emon
 */
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.travelbook.app.repository"})
@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createImageFolderInResources() {
        try {
            String separator;
            if (System.getProperties().getProperty("os.name").startsWith("Windows")) {
                separator = "\\";
            } else {
                separator = "/";
            }

            File mkdirs = new File("src/main/resources" + separator + "images");
            if (!mkdirs.exists()) {
                mkdirs.mkdirs();
            }
        } catch (Exception ex) {
            System.exit(0);
        }
    }
}
