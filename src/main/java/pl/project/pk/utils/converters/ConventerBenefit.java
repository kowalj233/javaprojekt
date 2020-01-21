package pl.project.pk.utils.converters;


import pl.project.pk.database.models.Benefit;
import pl.project.pk.mapper.BenefitMapper;

public class ConventerBenefit {

    public static BenefitMapper converToBenefitMapper(Benefit benefit){
        BenefitMapper benefitMapper = new BenefitMapper();
        benefitMapper.setId(benefit.getId());
        benefitMapper.setAmount(Long.toString(benefit.getAmount()));
        benefitMapper.setCategory(benefit.getCategory());
        benefitMapper.setEmployeeMapper( ConventerEmployee.convertToEmployeeMapper(benefit.getEmployee()) );

        return benefitMapper;
    }

}
