package place.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import place.SpringTestSupport;
import place.dto.entity.UserInfo;
import place.dto.kakao.format.KakaoResponse;
import place.repository.UserRepository;

import java.io.IOException;
import java.util.List;

@DisplayName("모듈 테스트")
public class CommonTest extends SpringTestSupport {


    @Test
    @DisplayName("JSON 파싱 테스트")
    public void jsonStringParsing() throws IOException {

        String jsonString = "{\n" +
                "\t\"documents\": [{\n" +
                "\t\t\"address_name\": \"서울 서초구 서초동 1305-7\",\n" +
                "\t\t\"category_name\": \"카카오프렌즈\",\n" +
                "\t\t\"id\": \"653245473\",\n" +
                "\t\t\"phone\": \"02-6494-1100\",\n" +
                "\t\t\"place_name\": \"카카오프렌즈 강남플래그십스토어\",\n" +
                "\t\t\"place_url\": \"http://place.map.kakao.com/653245473\",\n" +
                "\t\t\"road_address_name\": \"서울 서초구 강남대로 429\",\n" +
                "\t\t\"x\": \"127.025811882515\",\n" +
                "\t\t\"y\": \"37.5008934694709\"\n" +
                "\t}],\n" +
                "\t\"meta\": {\n" +
                "\t\t\"is_end\": false,\n" +
                "\t\t\"pageable_count\": 28,\n" +
                "\t\t\"same_name\": {\n" +
                "\t\t\t\"keyword\": \"카카오프렌즈\",\n" +
                "\t\t\t\"region\": [],\n" +
                "\t\t\t\"selected_region\": \"\"\n" +
                "\t\t},\n" +
                "\t\t\"total_count\": 28\n" +
                "\t}\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        KakaoResponse res =mapper.readValue(jsonString, KakaoResponse.class);
        res.getDocuments().get(0).setRedirectUrl("https://map.kakao.com/link/map/18577297");

        System.out.println(res.toString());
        System.out.println(res.getDocuments().get(0).getPlaceName());
        System.out.println(res.getMeta().getSameName().getKeyword());
    }
}
