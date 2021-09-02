package Model.Application;

import Model.Discount.SaleState;
import Model.Good.Good;

import java.util.ArrayList;
import java.util.Date;

public class CreatSaleApplication extends Application{
    private ArrayList<Good> inSaleGoods;
    private SaleState saleState;
    private Date startingDate;
    private Date endingDate;
    private double offPercent;

    public CreatSaleApplication(ApplicationType applicationType, ArrayList<Good> inSaleGoods, SaleState saleState, Date startingDate, Date endingDate, double offPercent) {
        super(applicationType);
        this.inSaleGoods = inSaleGoods;
        this.saleState = saleState;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.offPercent = offPercent;
    }
}
