package ch.unibas.ias.webcrawler.webcrawler;

public class SecurityRating {



    public static int calculate(MyDocument document, WordPressLoginSecurityStats stats, WordPressDangerousPlugins plugs) {
        int startvalue = 0;
        if(!document.getCMS().equals("?")) {
            startvalue++;
        }

        if(!document.getCMS().equals("?")) {
            startvalue++;
        }

        if(stats.getCanAccessAdminLogin()) {
            startvalue++;
        }

        if(stats.getHasRecaptcha()) {
            startvalue--;
        }

        if(stats.getHasJetpackPlugin()) {
            startvalue--;
        }

        if(stats.getHasProveYourHumanity()) {
            startvalue--;
        }

        if(stats.getHasForceStrongPassword()) {
            startvalue--;
        }

        if(plugs.isHasContactForm7()) {
            startvalue++;
        }

        if(plugs.isHasNextGENGallery()) {
            startvalue++;
        }

        if(plugs.isHasRedirection()) {
            startvalue++;
        }

        if(plugs.isHasWooCommerce()) {
            startvalue++;
        }

        if(plugs.isHasYoastSEO()) {
            startvalue++;
        }

        return startvalue;
    }



}
