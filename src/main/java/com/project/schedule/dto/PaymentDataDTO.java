package com.project.schedule.dto;

import lombok.*;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDataDTO {

    @NotNull(message = "날짜를 입력해주세요")
    @Size(min = 10, max = 50)
    private String regDate;

    @NotNull(message = "가입자수를 입력해주세요")
    @Size(min = 10, max = 50)
    private int registCount;

    @NotNull(message = "탈퇴자수를 입력해주세요")
    @Size(min = 10, max = 50)
    private int leaveCount;

    @NotNull(message = "결제금액을 입력해주세요")
    @Size(min = 10, max = 50)
    private long payAmount;

    @NotNull(message = "사용금액을 입력해주세요")
    @Size(min = 10, max = 50)
    private long usedAmount;

    @NotNull(message = "매출금액을 입력해주세요")
    @Size(min = 10, max = 50)
    private long salesAmount;


}
