package pl.project.pk.utils.factory;

import org.junit.Assert;
import org.junit.Test;


public class BenefitFactoryMethodTest {
    @Test
    public void makeLokata() throws Exception {
        BenefitFactoryMethod benefitFactoryMethod = new BenefitFactoryMethod();
        Assert.assertEquals(benefitFactoryMethod.makeFirstGroup().getClass(), new pl.project.pk.utils.benefit.FirstGroup().getClass());
    }

    @Test
    public void makeFundusz() throws Exception {
        BenefitFactoryMethod benefitFactoryMethod = new BenefitFactoryMethod();
        Assert.assertEquals(benefitFactoryMethod.makeSecondGroup().getClass(), new pl.project.pk.utils.benefit.SecondGroup().getClass());
    }

    @Test
    public void makeForex() throws Exception {
        BenefitFactoryMethod benefitFactoryMethod = new BenefitFactoryMethod();
        Assert.assertEquals(benefitFactoryMethod.makeThirdGroup().getClass(), new pl.project.pk.utils.benefit.ThirdGroup().getClass());
    }

}