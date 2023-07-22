package com.phongtq.brettspiel.auth.dto;

import com.phongtq.brettspiel.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Quach Thanh Phong
 * On 7/5/2023 - 10:40 AM
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class UserTokenDTO extends BaseDTO {

    @Schema(
            description = "Loại token. (Bearer)",
            example = "Bearer"
    )
    private String tokenType;

    @Schema(
            description = "Access Token dùng để request các api khác",
            example = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjU2MEZGRDY5NTQ4NzAwQjEzMUU1QTgxQTM1QzQ0ODg0NzFGMjczQ0RSUzI1NiIsIng1dCI6IlZnXzlhVlNIQUxFeDVhZ2FOY1JJaEhIeWM4MCIsInR5cCI6ImF0K2p3dCJ9.eyJpc3MiOiJodHRwczovL2lkLWRldi5mdGVjaC5haSIsIm5iZiI6MTY2Njg0Mjg0MywiaWF0IjoxNjY2ODQyODQzLCJleHAiOjE2NjY4NDMwMjMsImF1ZCI6WyJmaWQtYXBpIiwiZnBheS1hcGkiLCJmdW56eS1hcGkiLCJodHRwczovL2lkLWRldi5mdGVjaC5haS9yZXNvdXJjZXMiXSwic2NvcGUiOlsiZW1haWwiLCJmaWQtYXBpOmd1ZXN0IiwiZmlkLWFwaTppZCIsImZpZC1hcGk6cHJvZmlsZTplZGl0IiwiZnBheS1hcGk6aWFwLXVzZXI6Y3JlYXRlIiwiZnVuenktYXBpOmNsaWVudCIsIm9wZW5pZCIsInBob25lIiwicHJvZmlsZSIsIm9mZmxpbmVfYWNjZXNzIl0sImFtciI6WyJwd2QiXSwiY2xpZW50X2lkIjoicmFjaW5nLnNkayIsInN1YiI6IjEwMTQyMCIsImF1dGhfdGltZSI6MTY2Njg0Mjg0MywiaWRwIjoibG9jYWwiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJmaWRfMTJRUE9WWTAiLCJzaWQiOiIxNjUyOTAwOTI2MWY0MDg2YTZiNmViOTAzYWYzMjk1MSJ9.senmyZLgJ1DMckcog45kk_djQISSsI2xBlJ2l_DEFFQ009-o021uye_qYTxH60JoAzWi1-abzeQ2oVTGnMLBsiW6lv0NJ5omNle5-tiECYcYZ5E6T_rvx-SO6qENFkBHlVNtWBw2Be8txZKlhNhPUq6b1VoTYkBo2Cbo7SsOQb4W-oKAbGzBAyRvFIyMcRijRh6VFfCZhoFlBZjJZQEDvXZX6nvpDATJzNQZFXHiD1LwOuC0bf0gZpdBGo4skVOAmhLnC1Y4Z4d1HM8HQcCunCjLxavm2rWUO6QitVZsj4yA2hx_C6CQz50FmRjct5pQ5wuDHLP7DdIlKKTz4b9aBehb9sjbezXJKvZGNNX7-owkmSmuLm93peHwB3irN6ovgqE36CIMfxArYqnzmuGgWSuhqBrxRy6vDaM-KtphIla-weXd3QlNy7LJl_-0_yR7RFBHRJnr6kpjkOfPHERjqeepUzFYJYA3Ru5kBP8cabbAA0L1N9m7kbYThRJQAYVUVVvvNimxeH_qhpt4uHc2hXhMyr06F3b2Rn2bkv8ZErns4VTM_5k2W9SxLAVT-bK8qlbEo_WHjQG4rA_-VkMU52x5BTIOe_zDFR6F7vEmtr29XUR14EgMS0BSeyY6W56NhM8ahLI5YTtSHkMS7XkmGRrN5QNpzrbmoF1Z5nN_mR0"
    )
    private String accessToken;

    @Schema(
            description = "Refresh token dùng để lấy access token mới",
            example = "F5D5A2D437BA794581E8730F5A615D86AFF17438F5D59CA44198BC3012882F37-1"
    )
    private String refreshToken;

    @Schema(
            description = "Thời gian hiệu lực của access token",
            example = "180"
    )
    private Integer expiresIn;

}
