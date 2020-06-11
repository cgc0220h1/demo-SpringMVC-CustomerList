package repository;

import model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProvinceRepository extends PagingAndSortingRepository<Province, Long> {
}
