package com.dempe.analysis.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ResourceUtils {

    public static final String CLASSPATH_URL_PREFIX = "classpath:";
    public static final String FILE_URL_PREFIX = "file:";
    public static final String URL_PROTOCOL_FILE = "file";
    public static final String URL_PROTOCOL_JAR = "jar";
    public static final String URL_PROTOCOL_ZIP = "zip";
    public static final String URL_PROTOCOL_VFSZIP = "vfszip";
    public static final String URL_PROTOCOL_VFS = "vfs";
    public static final String URL_PROTOCOL_WSJAR = "wsjar";
    public static final String URL_PROTOCOL_CODE_SOURCE = "code-source";
    public static final String JAR_URL_SEPARATOR = "!/";

    public static boolean isUrl(String resourceLocation) {
        if (resourceLocation == null) {
            return false;
        }
        if (resourceLocation.startsWith("classpath:"))
            return true;
        try {
            new URL(resourceLocation);
            return true;
        } catch (MalformedURLException localMalformedURLException) {
        }
        return false;
    }

    public static URL getURL(String resourceLocation)
            throws FileNotFoundException {
        if (resourceLocation.startsWith("classpath:")) {
            String path = resourceLocation.substring("classpath:".length());
            URL url = ResourceUtils.class.getClassLoader().getResource(path);
            if (url == null) {
                String description = "class path resource [" + path + "]";
                throw new FileNotFoundException(
                        description
                                + " cannot be resolved to URL because it does not exist"
                );
            }
            return url;
        }
        try {
            return new URL(resourceLocation);
        } catch (MalformedURLException localMalformedURLException1) {
            try {
                return new File(resourceLocation).toURI().toURL();
            } catch (MalformedURLException localMalformedURLException2) {
                throw new FileNotFoundException("Resource location ["
                        + resourceLocation
                        + "] is neither a URL not a well-formed file path");
            }
        }
    }

    public static File getFile(String resourceLocation)
            throws FileNotFoundException {
        if (resourceLocation.startsWith("classpath:")) {
            String path = resourceLocation.substring("classpath:".length());
            String description = "class path resource [" + path + "]";
            URL url = ResourceUtils.class.getClassLoader().getResource(path);
            if (url == null) {
                throw new FileNotFoundException(description
                        + " cannot be resolved to absolute file path "
                        + "because it does not reside in the file system");
            }
            return getFile(url, description);
        }
        try {
            return getFile(new URL(resourceLocation));
        } catch (MalformedURLException localMalformedURLException) {
        }
        return new File(resourceLocation);
    }

    public static File getFile(URL resourceUrl) throws FileNotFoundException {
        return getFile(resourceUrl, "URL");
    }

    public static File getFile(URL resourceUrl, String description)
            throws FileNotFoundException {
        if (!("file".equals(resourceUrl.getProtocol())))
            throw new FileNotFoundException(description
                    + " cannot be resolved to absolute file path "
                    + "because it does not reside in the file system: "
                    + resourceUrl);
        try {
            return new File(toURI(resourceUrl).getSchemeSpecificPart());
        } catch (URISyntaxException localURISyntaxException) {
        }
        return new File(resourceUrl.getFile());
    }

    public static File getFile(URI resourceUri) throws FileNotFoundException {
        return getFile(resourceUri, "URI");
    }

    public static File getFile(URI resourceUri, String description)
            throws FileNotFoundException {
        if (!("file".equals(resourceUri.getScheme()))) {
            throw new FileNotFoundException(description
                    + " cannot be resolved to absolute file path "
                    + "because it does not reside in the file system: "
                    + resourceUri);
        }
        return new File(resourceUri.getSchemeSpecificPart());
    }

    public static boolean isJarURL(URL url) {
        String protocol = url.getProtocol();

        return (("jar".equals(protocol)) || ("zip".equals(protocol))
                || ("wsjar".equals(protocol)) || (("code-source"
                .equals(protocol)) && (url.getPath().contains("!/"))));
    }

    public static URL extractJarFileURL(URL jarUrl)
            throws MalformedURLException {
        String urlFile = jarUrl.getFile();
        int separatorIndex = urlFile.indexOf("!/");
        if (separatorIndex != -1) {
            String jarFile = urlFile.substring(0, separatorIndex);
            try {
                return new URL(jarFile);
            } catch (MalformedURLException localMalformedURLException) {
                if (!(jarFile.startsWith("/"))) {
                    jarFile = "/" + jarFile;
                }
                return new URL("file:" + jarFile);
            }
        }

        return jarUrl;
    }

    public static URI toURI(URL url) throws URISyntaxException {
        return toURI(url.toString());
    }

    public static URI toURI(String location) throws URISyntaxException {
        return new URI(replace(location, " ", "%20"));
    }

    public static String replace(String inString, String oldPattern,
                                 String newPattern) {
        if ((!(hasLength(inString))) || (!(hasLength(oldPattern)))
                || (newPattern == null)) {
            return inString;
        }
        StringBuilder sb = new StringBuilder();
        int pos = 0;
        int index = inString.indexOf(oldPattern);

        int patLen = oldPattern.length();
        while (index >= 0) {
            sb.append(inString.substring(pos, index));
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sb.append(inString.substring(pos));

        return sb.toString();
    }

    public static boolean hasLength(CharSequence str) {
        return ((str != null) && (str.length() > 0));
    }
}