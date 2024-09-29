package fr.silverpricing.api.repository;

import fr.silverpricing.api.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departement,Long> {
    @Query("SELECT d FROM Departement d WHERE d.deptcode = ?1")
    Departement findByCodeDept(String deptcode);
}
