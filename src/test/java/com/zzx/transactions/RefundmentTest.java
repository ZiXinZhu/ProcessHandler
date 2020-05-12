package com.zzx.transactions;

import com.zzx.transactions.entity.RefundmentDO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Description
 * @Author Alon
 * @Date 2020/5/12 22:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionsApplication.class)
@AutoConfigureMockMvc
public class RefundmentTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void before() {
        RefundmentDO refundmentDO = new RefundmentDO();
        refundmentDO.setId(1);
        refundmentDO.setOrderId("202004050000000012344321");
        refundmentDO.setRefundmentId("20200512000000002315424");
        refundmentDO.setMoney("1000");
        refundmentDO.setMark("refund");
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }

    @Test
    public void refund() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/refund")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("orderId", "202004050000000012344321")
                        .param("id", "1")
                        .param("refundmentId", "20200512000000002315424")
                        .param("money", "1000")
                        .param("mark", "refund")
                        .param("containEnum", "REFUNDMENT")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("", content);
    }
}
