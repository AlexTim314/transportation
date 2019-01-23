/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.utils;

import javax.servlet.ServletContext;
import org.springframework.http.MediaType;

/**
 *
 * @author alextim
 */
public class MediaTypeUtils {
    
        public MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
    
            String mineType = servletContext.getMimeType(fileName);
            try {
                MediaType mediaType = MediaType.parseMediaType(mineType);
                return mediaType;
            } catch (Exception e) {
                return MediaType.APPLICATION_OCTET_STREAM;
            }
        }
}
