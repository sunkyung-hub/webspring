//macbook_DAO
package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/*mapper.xml의 태그를 로드해옴 
 * DAO: 데이터를 Access 하는 역할 */

@Repository("macbook_DAO") //@Repository: Model을 Controller에 호출될 수 있도록 함
public class macbook_DAO implements macbook_mapper{ //@Mapper를 interface로드 하여 DAO작성
	
	//mybatis -> db연결
	@Resource(name="template")
	public SqlSessionTemplate st;

	//과정을 삭제처리하는 메소드
	@Override
	public int macbook_delete(int midx) {
		int result = this.st.delete("macbook_delete", midx);
		
		return result;
	}
	
	
	//하나의 데이터만 가져오는 메소드
	@Override
	public macbook_DTO macbook_one(String midx){
		//select 형태로 DB에 있는 데이터를 이관
		//selectOne("mapper.xml에서 사용하는 id명", 매개변수)
		System.out.println(midx);
		macbook_DTO onedata = this.st.selectOne("macbook_one", midx);
		return onedata;
	}
	
	@Override
	public List<macbook_DTO> macbook_select(){
		//selectOne: 데이터 한개만 가져올 때(List배열, ArrayList, DTO)
		//selectList: 데이터 여러개 가져올 때(List 배열로 가져옴)
		List<macbook_DTO> classList = this.st.selectList("macbook_select");
		return classList;
	}
	
	@Override
	public int macbook_insert(macbook_DTO dto) {
		int result = this.st.insert("macbook_insert", dto);
		return result;
	}
	
	@Override
	//데이터 수정 메소드
	public int macbook_update(macbook_DTO dto) {
		int result = this.st.update("macbook_update", dto);
		return result;
	}
}
