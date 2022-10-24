package com.project.schedule.service;

import com.project.schedule.common.Constants;
import com.project.schedule.entity.PaymentData;
import com.project.schedule.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FilenameUtils;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    private final PaymentRepository paymentRepository;

    public ScheduleService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    File dir = new File(Constants.path);
    List<PaymentData> list = new ArrayList<>();
    FileReader fileReader = null;
    BufferedReader bufReader = null;

    @Transactional
    public void saveFileJob() throws Exception {
        boolean isFind = false;

        String excludeUrl = Constants.suffix;
        for (File file : dir.listFiles()) {
            if (FilenameUtils.getExtension(file.getName()).contains(excludeUrl)) {
                isFind = true;
                break;
            }
        }

        if (!isFind) {
            for (File file : dir.listFiles()) {
                try {
                    //파일 입력 스트림 생성
                    fileReader = new FileReader(file);
                    bufReader = new BufferedReader(fileReader);
                    String line;

                    line = bufReader.readLine();
                    while ((line = bufReader.readLine()) != null) {
                        PaymentData bean = new PaymentData();
                        String[] array = line.split("\\|");     // | 구분자로 각 데이터 분리

                        bean.setRegDate(array[0].trim());
                        bean.setRegistCount(array[1].trim());
                        bean.setLeaveCount(array[2].trim());
                        bean.setPayAmount(array[3].trim());
                        bean.setUsedAmount(array[4].trim());
                        bean.setSalesAmount(array[5].trim());

                        list.add(bean);

                    }
                    paymentRepository.saveAll(list);
                } catch (FileNotFoundException e) {
                    throw e;
                } catch (Exception e) {
                    throw e;
                } finally {
                    fileReader.close();
                    bufReader.close();
                }

            }
        }
    }
}
