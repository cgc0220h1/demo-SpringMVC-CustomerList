package service.province;

import model.Province;
import service.IService;

public interface ProvinceService extends IService<Province> {
    Province findProvinceByName(String provinceName);
}
