package settings;

import javax.swing.ImageIcon;


import localization.Messages;
import domain.Setting;
public class Icons {

public enum IconEnum {
        AVAILABLE("available"),
        UNAVAILABLE("unavailable"),
        OK("ok"),
        OVERDUE("overdue"),
        BOOK("books"),
        CUSTOMER("customer"),
        LOAN("loan"),
        SETTING("settings"),
        ABOUT("about"),
        INVENTORY("inventory"),
        ADD("add"),
        INFORMATION("information"),
        EDITBOOK("book"),
        CANCEL("cancel"),
        SAVE("save"),
        DELETE("delete"),
        ADDLOAN("addloan"),
        CLOSELOAN("closeloan"),
        CUSTOMER_MAX("customer-max"),
        CUSTOMER_OK("customer-ok"),
        CUSTOMER_OVERDUE("customer-overdue"),
        EDITCUSTOMER("customer-info"),
        QUESTION("question"),
        CONDITION_GOOD("condition-ok"),
        CONDITION_BAD("condition-bad"),
        CUSTOMER_STATUS("customer-status"),
        LOGO("logo"),
        ERROR("error");
        
        private String fileName;
        private static final String resourceDir = "/resources/images/"; 
        private static final String imageEnding = ".png";
        
        IconEnum(String fileName) {
                this.fileName = fileName;
        }
        
        public ImageIcon getIcon() {
                return getIcon(16);
        }
        public ImageIcon getIcon(int size) {
                return new ImageIcon(Icons.class.getResource(resourceDir + fileName + "_" + size + imageEnding));
        }

}
public static ImageIcon getIcon(String purpose) {
        if (purpose.startsWith(Messages.getString("Domain.Book.Available"))) {
                return IconEnum.AVAILABLE.getIcon();
        }  else if (purpose.startsWith(Messages.getString("Domain.Book.Unavailable"))) {
                return IconEnum.UNAVAILABLE.getIcon();
        } else if (purpose.startsWith(Messages.getString("Domain.Loan.OK"))){
                return IconEnum.OK.getIcon();
        } else if (purpose.startsWith(Messages.getString("Domain.Loan.Overdue"))){
                return IconEnum.OVERDUE.getIcon();
        } else if (purpose.contains(Messages.getString("CustomersInventoryView.Overdue"))) {
                return IconEnum.CUSTOMER_OVERDUE.getIcon();
        } else if (purpose.substring(0, 1).matches("[0-9]")) {
                if (Integer.parseInt(purpose.substring(0, 1)) == Setting.getMaxBorrowsPerCustomer()) {
                        return IconEnum.CUSTOMER_MAX.getIcon();
                } else {
                        return IconEnum.CUSTOMER_OK.getIcon();
                }
        }
        else {
                return null;
        }
}
}