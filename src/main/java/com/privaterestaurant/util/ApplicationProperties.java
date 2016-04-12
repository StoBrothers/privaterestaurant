package com.privaterestaurant.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.privaterestaurant.RestaurantApplication;

/**
 * Application settings component.
 * 
 * @author Sergey Stotskiy
 *
 */
@Component("applicationProperties")
public class ApplicationProperties {

    private static boolean testServer;
    private static boolean localProfile;
    private static boolean devProfile;
    private static String activeProfiles;
    private static String buildVersion;
    private static String buildDate;
    
    private static int plusHour;
    private static int minusHour;
    
    

    private ApplicationProperties() {
    }

    @Value("${privaterestaurant.testserver}")
    private void setTestServer(boolean testServer) {
        ApplicationProperties.testServer = testServer;
    }

    @Value("${spring.profiles:}")
    private void setActiveProfiles(String activeProfiles) {
        ApplicationProperties.activeProfiles = activeProfiles;
        if (StringUtils.isEmpty(activeProfiles)
            || activeProfiles.equals(RestaurantApplication.PROFILE_LOCALDEBUG)) {
            ApplicationProperties.localProfile = true;
        } else if (activeProfiles != null
            && activeProfiles.contains(RestaurantApplication.PROFILE_DEV)) {
            ApplicationProperties.devProfile = true;
        }
    }

    @Value("${privaterestaurant.build.version}")
    private void setBuildVersion(String buildVersion) {
        ApplicationProperties.buildVersion = buildVersion;
    }

    @Value("${privaterestaurant.build.date}")
    private void setBuildDate(String buildDate) {
        ZonedDateTime zdt = ZonedDateTime.parse(buildDate,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DateUtil.DATE_FULL_FORMAT);
        ApplicationProperties.buildDate = dtf
            .format(zdt.plusSeconds(zdt.getOffset().getTotalSeconds()));
    }

	@Value("${privaterestaurant.votetime.plushour}")
	private void setPlusHour(int plushour) {
		ApplicationProperties.plusHour = plushour;
	}

	@Value("${privaterestaurant.votetime.minushour}")
	private void setMinusHour(int minushour) {
		ApplicationProperties.minusHour = minushour;
	}
	
    public static boolean isTestserver() {
        return testServer;
    }

    public static boolean isLocalProfile() {
        return localProfile;
    }

    public static void setLocalProfile(boolean localProfile) {
        ApplicationProperties.localProfile = localProfile;
    }

    public static boolean isDevProfile() {
        return devProfile;
    }

    public static void setDevProfile(boolean devProfile) {
        ApplicationProperties.devProfile = devProfile;
    }

    public static String getActiveProfiles() {
        return activeProfiles;
    }

    public static String getBuildVersion() {
        return buildVersion;
    }

    public static String getBuildDate() {
        return buildDate;
    }

	public static int getPlusHour() {
		return plusHour;
	}

	public static int getMinusHour() {
		return minusHour;
	}

}
