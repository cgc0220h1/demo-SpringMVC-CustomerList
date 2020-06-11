package service.province;

import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.ProvinceRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceImp implements ProvinceService {
    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceServiceImp(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public Province save(Province province) {
        return provinceRepository.save(province);
    }

    @Override
    public List<Province> saveAll(List<Province> provinces) {
        List<Province> savedProvince = new LinkedList<>();
        for (Province province : provinces) {
            savedProvince.add(provinceRepository.save(province));
        }
        return savedProvince;
    }

    @Override
    public Province findById(Long id) {
        Optional<Province> provinceOptional = provinceRepository.findById(id);
        return provinceOptional.orElse(null);
    }

    @Override
    public Province findProvinceByName(String provinceName) {
        return null;
    }

    @Override
    public Page<Province> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return provinceRepository.existsById(id);
    }

    @Override
    public List<Province> findAll() {
        Iterable<Province> iterable = provinceRepository.findAll();
        List<Province> allProvince = new LinkedList<>();
        for (Province province : iterable) {
            allProvince.add(province);
        }
        return allProvince;
    }

    @Override
    public List<Province> findAllById(List<Long> ids) {
        List<Province> provincesFound = new LinkedList<>();
        for (Long id: ids) {
            provincesFound.add(findById(id));
        }
        return provincesFound;
    }

    @Override
    public long count() {
        return provinceRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        provinceRepository.deleteById(id);
    }

    @Override
    public void delete(Province province) {
        provinceRepository.delete(province);
    }

    @Override
    public void deleteAll(List<Province> provinces) {
        for (Province province : provinces) {
            provinceRepository.delete(province);
        }
    }

    @Override
    public void deleteAll() {
        provinceRepository.deleteAll();
    }
}
