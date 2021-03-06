/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.kit.isco.KitAlumniApp.server.gcm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Context initializer that loads the API key from a
 * {@value #PATH} file located in the classpath (typically under
 * {@code WEB-INF/classes}).
 */
@WebListener 
public class ApiKeyInitializer implements ServletContextListener {

  static final String ATTRIBUTE_ACCESS_KEY = "apiKey";

  private static final String PATH = "api.key";
  
  private static ServletContext context;

  private final Logger logger = Logger.getLogger(getClass().getName());

  public void contextInitialized(ServletContextEvent event) {
    logger.info("Reading " + PATH + " from resources (probably from " +
        "WEB-INF/classes");
    String key = getKey();
    context = event.getServletContext();
    context.setAttribute(ATTRIBUTE_ACCESS_KEY, key);
  }
  
  public static String getAccessKey() {
	  return ATTRIBUTE_ACCESS_KEY;
  }
  
  public static String getApiKey() {
	  return (String) context.getAttribute(ATTRIBUTE_ACCESS_KEY);
  }

  /**
   * Gets the access key.
   */
  protected String getKey() {
    InputStream stream = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(PATH);
    if (stream == null) {
      throw new IllegalStateException("Could not find file " + PATH +
          " on web resources)");
    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    try {
      String key = reader.readLine();
      return key;
    } catch (IOException e) {
      throw new RuntimeException("Could not read file " + PATH, e);
    } finally {
      try {
        reader.close();
      } catch (IOException e) {
        logger.log(Level.WARNING, "Exception closing " + PATH, e);
      }
    }
  }

 public void contextDestroyed(ServletContextEvent event) {}

}
