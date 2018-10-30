package io.picty.api.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class BuildDetails {

    private String buildVendor;
    private String buildNumber;
    private String buildTime;
    private String buildCodeName;
    private String builtBy;
    private String buildJdk;

    public BuildDetails() {

        buildVendor = getManifestInfo("Implementation-Vendor-Id");
        buildNumber = getManifestInfo("Implementation-Version");
        buildTime = getManifestInfo("Build-Time");
        buildCodeName = getManifestInfo("Build-CodeName");
        builtBy = getManifestInfo("Built-By");
        buildJdk = getManifestInfo("Build-Jdk");

        if (buildNumber == null) {
            buildNumber = "Unknown build";
        }

    }

    public String getBuildVendor() {
        return buildVendor;
    }

    public void setBuildVendor(String buildVendor) {
        this.buildVendor = buildVendor;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getBuildCodeName() {
        return buildCodeName;
    }

    public void setBuildCodeName(String buildCodeName) {
        this.buildCodeName = buildCodeName;
    }

    public String getBuiltBy() {
        return builtBy;
    }

    public void setBuiltBy(String builtBy) {
        this.builtBy = builtBy;
    }

    public String getBuildJdk() {
        return buildJdk;
    }

    public void setBuildJdk(String buildJdk) {
        this.buildJdk = buildJdk;
    }

    private String getManifestInfo(String manifestAttribute) {
        Enumeration resEnum;
        try {
            resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
            while (resEnum.hasMoreElements()) {
                try {
                    URL url = (URL)resEnum.nextElement();
                    InputStream is = url.openStream();
                    if (is != null) {
                        Manifest manifest = new Manifest(is);
                        Attributes mainAttribs = manifest.getMainAttributes();
                        String version = mainAttribs.getValue(manifestAttribute);
                        if(version != null) {
                            return version;
                        }
                    }
                }
                catch (Exception e) {
                    // Silently ignore wrong manifests on classpath
                }
            }
        } catch (IOException e1) {
            // Silently ignore wrong manifests on classpath
        }
        return null;
    }

    @Override
    public String toString() {
        return "BuildDetails{" +
                "buildVendor='" + buildVendor + '\'' +
                ", buildNumber='" + buildNumber + '\'' +
                ", buildTime='" + buildTime + '\'' +
                ", buildCodeName='" + buildCodeName + '\'' +
                ", builtBy='" + builtBy + '\'' +
                ", buildJdk='" + buildJdk + '\'' +
                '}';
    }
}
