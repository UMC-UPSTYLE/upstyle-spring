
import com.upstyle.upstyle.domain.ClothKind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothKindRepository extends JpaRepository<ClothKind, Long> {
}

