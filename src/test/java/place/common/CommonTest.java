package place.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import place.dto.kakao.format.KakaoResponse;

import java.io.IOException;

public class CommonTest {

    @Test
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
