package domain;

import java.util.Observable;

public class Setting extends Observable {
        private static int maxOpenDetail = 5;
        private static int maxBorrowsPerCustomer = 3;

// maxOpenDetail - edits
        public static void setMaxOpenDetail(int u_maxOpenDetail) {
                maxOpenDetail = u_maxOpenDetail;
        }
        public static int getMaxOpenDetail() {
                return maxOpenDetail;
        }
        
// maxBorrowsPerCustomer - edits
        public static void setMaxBorrowsPerCustomer(int u_maxBorrowsPerCustomer) {
                maxBorrowsPerCustomer = u_maxBorrowsPerCustomer;
        }
        public static int getMaxBorrowsPerCustomer() {
                return maxBorrowsPerCustomer;
        }

}