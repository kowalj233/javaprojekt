package pl.project.pk.utils.benefit;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BenefitBaseTest {
    private static List<BenefitBase> benefitList = new ArrayList<BenefitBase>();

    @Test
    public void benefitType() throws Exception {
        /* iterator w testach */

        benefitList.add(new FirstGroup());
        benefitList.add(new SecondGroup());
        benefitList.add(new ThirdGroup());

        benefitList.add(new FirstGroup());
        benefitList.add(new SecondGroup());
        benefitList.add(new ThirdGroup());

        Iterator<BenefitBase> it = benefitList.iterator();
        while(it.hasNext()) Assert.assertNotNull(it.next().benefitType());

    }

}