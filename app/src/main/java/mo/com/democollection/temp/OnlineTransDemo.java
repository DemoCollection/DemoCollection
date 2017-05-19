package mo.com.democollection.temp;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;



/**
 * 联机接口
 * @author LiuDezhuang
 *
 */
public class OnlineTransDemo  extends TestCase {


    //访问接口地址
    private static String uri = "https://test.credit2go.cn/escrow/p2p/online";
    //版本号
    private static final String VERSION = "10";
    //银行代码
    private static final String BANKCODE = "30050000";
    //机构代码   （说明：平台需要根据即信端给定实际参数进行调整）     
    private static final String INSTCODE = "00170001";
    //交易渠道
    private static final String COINSTCHANNEL = "000002";

    /**
     * 电子账户余额查询
     * @throws Exception
     */
    
    public void testBalanceQuery() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);

        reqMap.put("txCode", "balanceQuery");
        reqMap.put("accountId", "6212461270000004701");

        testCommon(reqMap);
    }

    /**
     * 个人开户
     * @throws Exception
     */
    
    public void testAccountOpen() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);

        reqMap.put("txCode", "accountOpen");
        reqMap.put("idType","01");
        reqMap.put("idNo","110101199801012211");
        reqMap.put("name","柳永");
        reqMap.put("mobile","18242610829");
        reqMap.put("cardNo","6222988812340036");
        reqMap.put("email","");
        reqMap.put("acctUse","00000");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 按证件号查询电子账号
     * @throws Exception
     */
    
    public void testAccountIdQuery() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);

        reqMap.put("txCode", "accountIdQuery");
        reqMap.put("idType","01");
        reqMap.put("idNo","110101199801012211");

        testCommon(reqMap);
    }

    /**
     * 电子账户资金交易明细查询
     * @throws Exception
     */
    
    public void testAccountDetailsQuery() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "accountDetailsQuery");

        reqMap.put("accountId","6212461270000004701");
        reqMap.put("startDate","20150823");
        reqMap.put("endDate","20150825");
        reqMap.put("type","0");
        reqMap.put("tranType","");
        reqMap.put("pageNum","1");
        reqMap.put("pageSize","10");

        testCommon(reqMap);
    }

    /**
     * 绑卡关系查询
     * @throws Exception
     */
    
    public void testCardBind() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        reqMap.put("txCode", "cardBind");
        getHeadReq(reqMap);

        reqMap.put("accountId","6212461170000000909");
        reqMap.put("idType","01");
        reqMap.put("idNo","370303198803156013");
        reqMap.put("name","李一一");
        reqMap.put("mobile","15242610829");
        reqMap.put("cardNo","6226620402564890018");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 解绑银行卡
     * @throws Exception
     */
    
    public void testCardUnbind() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        reqMap.put("version", VERSION);
        reqMap.put("txCode", "cardUnbind");
        reqMap.put("instCode", INSTCODE);
        reqMap.put("bankCode", BANKCODE);
        reqMap.put("txDate", DateUtil.getDate());
        reqMap.put("txTime", DateUtil.getTime());
        reqMap.put("seqNo", "100101");
        reqMap.put("channel", COINSTCHANNEL);
        reqMap.put("accountId","6212461170000000909");
        reqMap.put("idType","01");
        reqMap.put("idNo","370303198803156013");
        reqMap.put("name","李一一");
        reqMap.put("mobile","15242610829");
        reqMap.put("cardNo","6226620402564890018");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 资金冻结
     * 功能说明：冻结用户电子账户中指定金额
     * @throws Exception
     */
    
    public void testBalanceFreeze() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        reqMap.put("version", VERSION);
        reqMap.put("txCode", "balanceFreeze");
        reqMap.put("instCode", INSTCODE);
        reqMap.put("bankCode", BANKCODE);
        reqMap.put("txDate", DateUtil.getDate());
        reqMap.put("txTime", DateUtil.getTime());
        reqMap.put("seqNo", "100101");
        reqMap.put("channel", COINSTCHANNEL);
        reqMap.put("accountId","6212461170000000362");
        reqMap.put("orderId","123459");
        reqMap.put("txAmount","10");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 资金解冻
     * 功能说明：撤销原来冻结的电子账户资金
     * @throws Exception
     */
    
    public void testBalanceUnfreeze() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        reqMap.put("version", VERSION);
        reqMap.put("txCode", "balanceUnfreeze");
        reqMap.put("instCode", INSTCODE);
        reqMap.put("bankCode", BANKCODE);
        reqMap.put("txDate", DateUtil.getDate());
        reqMap.put("txTime", DateUtil.getTime());
        reqMap.put("seqNo", "100101");
        reqMap.put("channel", COINSTCHANNEL);
        reqMap.put("accountId","6212461170000000362");
        reqMap.put("orderId","123452");
        reqMap.put("txAmount","11");
        reqMap.put("orgOrderId","123459");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 借款人标的登记
     * 功能说明：借款人登记标的信息。
     * @throws Exception
     */
    
    public void testDebtRegister() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        reqMap.put("version", VERSION);
        reqMap.put("txCode", "debtRegister");
        reqMap.put("instCode", INSTCODE);
        reqMap.put("bankCode", BANKCODE);
        reqMap.put("txDate", DateUtil.getDate());
        reqMap.put("txTime", DateUtil.getTime());
        reqMap.put("seqNo", "100101");
        reqMap.put("channel", COINSTCHANNEL);
        reqMap.put("accountId","6212461170000000362");
        reqMap.put("productId","000012");
        reqMap.put("productDesc","");
        reqMap.put("raiseDate","20160703");
        reqMap.put("raiseEndDate","20160703");
        reqMap.put("intType","0");
        reqMap.put("intPayDay","");
        reqMap.put("duration","10");
        reqMap.put("txAmount","1000");
        reqMap.put("rate","10.00");
        reqMap.put("txFee","10");
        reqMap.put("bailAccountId","");
        reqMap.put("bailFee","");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 借款人标的撤销
     * 功能说明：借款人撤销登记标的信息，若有投标，必须先撤销所有投标信息。
     * @throws Exception
     */
    
    public void testDebtRegisterCancel() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        reqMap.put("version", VERSION);
        reqMap.put("txCode", "debtRegisterCancel");
        reqMap.put("instCode", INSTCODE);
        reqMap.put("bankCode", BANKCODE);
        reqMap.put("txDate", DateUtil.getDate());
        reqMap.put("txTime", DateUtil.getTime());
        reqMap.put("seqNo", "100101");
        reqMap.put("channel", COINSTCHANNEL);
        reqMap.put("accountId","6212461170000000362");
        reqMap.put("productId","000011");
        reqMap.put("raiseDate","20160712");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 投资人自动投标签约
     * 功能说明：投资人与P2P平台签订自动投标协议，P2P平台可以代投资人投标
     * 调用方式：页面调用，投资人在页面上输入手机验证码
     * @throws Exception
     */
    
    public void testAutoBidAuth() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "autoBidAuth");

        reqMap.put("accountId","6212461170000000362");
        reqMap.put("orderId","123456");
        reqMap.put("txAmount","60");
        reqMap.put("totAmount","100");
        reqMap.put("retUrl","");
        reqMap.put("notifyUrl","");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 撤销自动投标签约
     * 功能说明：撤销与P2P平台签订的自动投标协议
     * @throws Exception
     */
    
    public void testAutoBidAuthCancel() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "autoBidAuthCancel");

        reqMap.put("accountId","6212461170000000362");
        reqMap.put("orderId","");
        reqMap.put("txAmount","");
        reqMap.put("txFee","");
        reqMap.put("productId","");
        reqMap.put("frzFlag","");
        reqMap.put("contOrderId","");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    
    public void testBidCancel() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "bidCancel");

        reqMap.put("accountId","6212461170000000362");
        reqMap.put("orderId","");
        reqMap.put("txAmount","");
        reqMap.put("txFee","");
        reqMap.put("productId","");
        reqMap.put("orgOrderId","");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 放款
     * 功能说明：投资人投标以后，P2P平台通过本交易申请将资金从投资人电子账户划转到融资人电子账户，
     *        实际生效的时间视银行处理情况而定。
     * @throws Exception
     */
    
    public void testLendPay() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "lendPay");

        reqMap.put("accountId","6212461170000000362");
        reqMap.put("orderId","");
        reqMap.put("txAmount","");
        reqMap.put("bidFee","");
        reqMap.put("debtFee","");
        reqMap.put("bailFee","");
        reqMap.put("forAccountId","");
        reqMap.put("productId","");
        reqMap.put("authCode","");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * Repay
     * 功能说明：融资人向投资人还款，P2P平台通过本交易申请将资金从融资人电子账户划转到投资人电子账户，
     *        实际生效的时间视银行处理情况而定。
     * @throws Exception
     */
    
    public void testRepay() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "repay");

        reqMap.put("accountId","6212461170000000362");
        reqMap.put("orderId","");
        reqMap.put("txAmount","");
        reqMap.put("intAmount","");
        reqMap.put("txFeeOut","");
        reqMap.put("txBailFee","");
        reqMap.put("txFeeIn","");
        reqMap.put("forAccountId","");
        reqMap.put("productId","");
        reqMap.put("authCode","");
        reqMap.put("freezeFlag","");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 自动投标申请
     * 功能说明：P2P平台自动为投资人投标
     * @throws Exception
     */
    
    public void testBidAutoApply() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "bidAutoApply");

        reqMap.put("accountId","6212461170000000362");
        reqMap.put("orderId","");
        reqMap.put("txAmount","");
        reqMap.put("txFee","");
        reqMap.put("productId","");
        reqMap.put("frzFlag","");
        reqMap.put("contOrderId","");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 自动购买债权
     * 功能说明：P2P平台自动为签约投资人购买债权，资金会实时从债权的购买方电子账户转到卖出方电子账户
     * @throws Exception
     */
    
    public void testCreditAutoInvest() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "creditAutoInvest");

        reqMap.put("accountId","6212461170000000362");
        reqMap.put("orderId","");
        reqMap.put("txAmount","");
        reqMap.put("txFee","");
        reqMap.put("tsfAmount","");
        reqMap.put("forAccountId","");
        reqMap.put("orgOrderId","");
        reqMap.put("orgTxAmount","");
        reqMap.put("productId","");
        reqMap.put("contOrderId","");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 红包发放
     * 功能说明：P2P平台可以从红包电子账户向其他电子账户以发红包的方式转移资金
     * @throws Exception
     */
    
    public void testVoucherPay() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "voucherPay");

        reqMap.put("accountId","6212461170000000362");
        reqMap.put("txAmount","");
        reqMap.put("forAccountId","");
        reqMap.put("desLineFlag","");
        reqMap.put("desLine","");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 红包发放撤销
     * 功能说明：P2P平台撤销红包发放（仅银行系统的当日有效），资金从发放目标账户回到红包账户
     * @throws Exception
     */
    
    public void testVoucherPayCancel() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "voucherPayCancel");

        reqMap.put("accountId","6212461170000000362");
        reqMap.put("txAmount","");
        reqMap.put("forAccountId","");
        reqMap.put("orgTxDate","");
        reqMap.put("orgTxTime","");
        reqMap.put("orgSeqNo","");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 结束债权
     * 功能说明：结束某笔债权，P2P平台通过本交易申请结束一笔投资人持有的债权，
     *        实际生效的时间视银行处理情况而定。
     * @throws Exception
     */
    

    public void testCreditEnd() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "creditEnd");

        reqMap.put("accountId","6212461170000000362");
        reqMap.put("orderId","");
        reqMap.put("forAccountId","");
        reqMap.put("productId","");
        reqMap.put("authCode","");
        reqMap.put("acqRes","");
        testCommon(reqMap);
    }

    /**
     * 投资人债权明细查询
     * 功能说明：查询投资人电子账户名下的债权明细（包括投标中的债权）
     * @throws Exception
     */
    
    public void testcreditDetailsQuery() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "creditDetailsQuery");

        reqMap.put("accountId","6212461380000000508");
        reqMap.put("productId","");
        reqMap.put("state","0");
        reqMap.put("startDate","20160705");
        reqMap.put("endDate","20160810");
        reqMap.put("pageNum","1");
        reqMap.put("pageSize","30");
        testCommon(reqMap);
    }

    /**
     * 担保账户代偿
     * 功能说明：通过担保账户向投资人还款，资金会实时从担保电子账户转到投资人账户
     * @throws Exception
     */
    
    public void testBailRepay() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "bailRepay");

        reqMap.put("accountId","6212461170000000362");
        reqMap.put("orderId","");
        reqMap.put("txAmount","");
        reqMap.put("txCapAmout","");
        reqMap.put("txIntAmount","");
        reqMap.put("forAccountId","");
        reqMap.put("orgOrderId","");
        reqMap.put("orgTxAmount","");
        reqMap.put("productId","");
        reqMap.put("acqRes","");
        testCommon(reqMap);
    }

    /**
     * 融资人还担保账户垫款
     * 功能说明：融资人向担保账户还款，P2P平台通过本交易申请将资金从融资人电子账户划转到担保账户，
     *        实际生效的时间视银行处理情况而定。
     * @throws Exception
     */
    
    public void testRepayBail() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "repayBail");


        reqMap.put("accountId","6212461170000000362");
        reqMap.put("orderId","");
        reqMap.put("txAmount","");
        reqMap.put("intAmount","");
        reqMap.put("txFeeOut","");
        reqMap.put("forAccountId","");
        reqMap.put("orgOrderId","");
        reqMap.put("authCode","");
        reqMap.put("freezeFlag","");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 绑卡关系查询
     * 功能说明：查询用户名下绑定卡的情况（仅有一张卡有效，仅保留最近十笔）
     * @throws Exception
     */
    
    public void testCardBindDetailsQuery() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "cardBindDetailsQuery");

        reqMap.put("accountId","6212461170000000362");

        testCommon(reqMap);
    }


    /**
     * 借款人标的信息查询
     * 功能说明：借款人登记的标的信息查询。
     * @throws Exception
     */
    
    public void testDebtDetailsQuery() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "debtDetailsQuery");

        reqMap.put("accountId","6212461170000000362");
        reqMap.put("productId","");
        reqMap.put("startDate","");
        reqMap.put("endDate","");
        reqMap.put("pageNum","");
        reqMap.put("pageSize","");

        testCommon(reqMap);
    }


    /**
     * 电子账户手机号查询维护
     * 功能说明：查询或修改电子账户的手机号，P2P平台应在调用前校验手机验证码
     * @throws Exception
     */
    
    public void testMobileMaintainace() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "mobileMaintainace");

        reqMap.put("accountId","6212461170000000362");
        reqMap.put("option","");
        reqMap.put("mobile","");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 按手机号查询电子账号信息
     * 功能说明：根据手机号查询电子账户信息
     * @throws Exception
     */
    
    public void testAccountQueryByMobile() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "accountQueryByMobile");

        reqMap.put("mobile","18242610829");

        testCommon(reqMap);
    }

    /**
     * 查询交易状态
     * 功能说明：根据交易流水号或者订单号查询交易的状态（可以查询放款、还款交易）
     * @throws Exception
     */
    
    public void testTransactionStatusQuery() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "transactionStatusQuery");

        reqMap.put("accountId","");
        reqMap.put("reqType","");
        reqMap.put("reqTxCode","");
        reqMap.put("reqTxDate","");
        reqMap.put("reqTxTime","");
        reqMap.put("reqSeqNo","");
        reqMap.put("reqOrderId","");

        testCommon(reqMap);
    }

    /**
     * 查询批次状态
     * 功能说明：查询批次的状态，包括批次放款、批次还款、批次融资人还担保账户垫款接口、批次结束债权。
     * @throws Exception
     */
    
    public void testBatchQuery() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "batchQuery");

        reqMap.put("batchTxDate","");
        reqMap.put("batchNo","");

        testCommon(reqMap);
    }

    /**
     * 批次放款
     * 功能说明：投资人投标以后，P2P平台通过本交易申请将资金从投资人电子账户划转到融资人电子账户，
     *        实际生效的时间视银行处理情况而定，支持多笔交易，同一个批次号的交易一起处理，但是可能仅部分交易成功。
     *        后台收到请求以后，异步通知请求方报文收取和合法性判断的结果，业务处理也异步通知到相应的URL，
     *        或者请求方可以主动查询。
     * @throws Exception
     */
    
    public void testBatchLendPay() throws Exception{
        List<Map<String, String>> arrayList = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("accountId","");
        map1.put("orderId","");
        map1.put("txAmount","");
        map1.put("bidFee","");
        map1.put("debtFee","");
        arrayList.add(map1);

        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "batchLendPay");

        reqMap.put("batchNo","");
        reqMap.put("txAmount","");
        reqMap.put("txCounts","");
        reqMap.put("notifyURL","");
        reqMap.put("retNotifyURL","");
        reqMap.put("acqRes","");
        reqMap.put("subPacks",arrayList.toString());

        testCommon(reqMap);
    }

    /**
     * 批次还款
     * 功能说明：融资人向投资人还款，P2P平台通过本交易申请将资金从融资人电子账户划转到投资人电子账户，
     *        实际生效的时间视银行处理情况而定，支持多笔交易，同一个批次号的交易一起处理，但是可能仅部分交易成功。
     *        后台收到请求以后，异步通知请求方报文收取和合法性判断的结果，业务处理也异步通知到相应的URL，
     *        或者请求方可以主动查询。本交易不会主动冻结融资人资金，如需要先冻结，请调用“资金冻结”接口。
     * @throws Exception
     */
    
    public void testBatchRepay() throws Exception{
        List<Map<String, String>> arrayList = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("accountId","");
        map1.put("orderId","");
        map1.put("forAccountId","");
        map1.put("productId","");
        map1.put("authCode","");
        arrayList.add(map1);

        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "batchRepay");

        reqMap.put("batchNo","");
        reqMap.put("txCounts","");
        reqMap.put("notifyURL","");
        reqMap.put("retNotifyURL","");
        reqMap.put("acqRes","");
        reqMap.put("subPacks",arrayList.toString());

        testCommon(reqMap);
    }

    /**
     * 批次结束债权
     * 功能说明：结束债权，P2P平台通过本交易申请结束一笔投资人持有的债权，
     *        实际生效的时间视银行处理情况而定，支持多笔交易，同一个批次号的交易一起处理，但是可能仅部分交易成功。
     *        后台收到请求以后，异步通知请求方报文收取和合法性判断的结果，业务处理也异步通知到相应的URL，或者请求方可以主动查询。
     * @throws Exception
     */
    
    public void testBatchCreditEnd() throws Exception{
        List<Map<String, String>> arrayList = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("accountId","");
        map1.put("orderId","");
        map1.put("forAccountId","");
        map1.put("productId","");
        map1.put("authCode","");

        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "batchCreditEnd");

        reqMap.put("batchNo","");
        reqMap.put("txCounts","");
        reqMap.put("notifyURL","");
        reqMap.put("retNotifyURL","");
        reqMap.put("acqRes","");
        reqMap.put("subPacks",arrayList.toString());

        testCommon(reqMap);
    }

    /**
     * 放款或还款撤销
     * 功能说明：P2P平台在放款、还款、融资人还担保账户垫款未被集中处理前可以撤销该交易。
     * @throws Exception
     */
    
    public void testPayCancel() throws Exception{

        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "payCancel");

        reqMap.put("accountId","");
        reqMap.put("txAmount","");
        reqMap.put("forAccountId","");
        reqMap.put("orgTxDate","");
        reqMap.put("orgTxTime","");
        reqMap.put("orgSeqNo","");
        reqMap.put("orgTxCode","");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }


    /**
     * 查询批次交易明细状态
     * 功能说明：查询批次交易明细的状态，包括批次放款、批次还款、批次融资人还担保账户垫款接口、批次结束债权。
     * @throws Exception
     */
    
    public void testBatchDetailsQuery() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "batchDetailsQuery");

        reqMap.put("batchTxDate","");
        reqMap.put("batchNo","");
        reqMap.put("type","");
        reqMap.put("pageNum","");
        reqMap.put("pageSize","");

        testCommon(reqMap);
    }

    /**
     * 投资人购买债权查询
     * 功能说明：查询单笔投资人购买债权。
     * @throws Exception
     */
    
    public void testCreditInvestQuery() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "creditInvestQuery");

        reqMap.put("accountId","");
        reqMap.put("orgOrderId","");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 请求发送短信验证码
     * 功能说明：向指定的短信号码发送验证码。
     * @throws Exception
     */
    
    public void testSmsCodeApply() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "smsCodeApply");

        reqMap.put("mobile","18171025631");
        reqMap.put("srvTxCode","accountOpenPlus");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 个人开户增强  6212461270000004719
     * 功能说明：验证短信验证码，在账务系统中开户，同时绑定同名银行卡。必须银行卡对应的姓名、证件与输入信息一致。
     * @throws Exception
     */
    
    public void testAccountOpenPlus() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "accountOpenPlus");

        reqMap.put("idType","01");
        reqMap.put("idNo","110101199801011956");
        reqMap.put("name","杜甫");
        reqMap.put("mobile","18171025631");
        reqMap.put("cardNo","6222988812340036");
        reqMap.put("email","");
        reqMap.put("acctUse","00000");
        reqMap.put("lastSrvAuthCode","2096592745463737764");
        reqMap.put("smsCode","111111");
        reqMap.put("userIP","192.168.1.1");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 绑定银行卡增强
     * 功能说明：验证短信验证码，当电子账户在账务系统没有绑定银行卡时，
     *        使用该交易绑定同名银行卡。必须银行卡对应的姓名、证件与输入信息一致。
     * @throws Exception
     */
    
    public void testCardBindPlus() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "cardBindPlus");

        reqMap.put("accountId","6212461270000004719");
        reqMap.put("idType","01");
        reqMap.put("idNo","110101199801011956");
        reqMap.put("name","杜甫");
        reqMap.put("mobile","18171025631");
        reqMap.put("cardNo","6222988812340036");
        reqMap.put("lastSrvAuthCode","2096592745463737764");
        reqMap.put("smsCode","111111");
        reqMap.put("userIP","192.168.1.1");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 电子账户手机号修改增强
     * 功能说明：验证短信验证码，修改电子账户的手机号。
     * @throws Exception
     */
    
    public void testMobileModifyPlus() throws Exception{
        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "mobileModifyPlus");

        reqMap.put("accountId","6212461270000004719");
        reqMap.put("option","1");
        reqMap.put("mobile","18171025631");
        reqMap.put("lastSrvAuthCode","2096592745463737764");
        reqMap.put("smsCode","111111");
        reqMap.put("userIP","192.168.1.1");
        reqMap.put("acqRes","");

        testCommon(reqMap);
    }

    /**
     * 批次投资人购买债权
     * 功能说明：投资人从其他投资人名下购买债权，实际生效的时间视银行处理情况而定，支持多笔交易，
     *       同一个批次号的交易一起处理，但是可能仅部分交易成功。后台收到请求以后，同步回应接收结果，
     *       异步通知请求方报文收取和合法性判断的结果（P2P平台收到后回应success表示收到异步通知），
     *       业务处理也异步通知到相应的URL（P2P平台收到后回应success表示收到异步通知），或者请求方可以主动查询。
     * @throws Exception
     */
    
    public void testBatchCreditInvest() throws Exception{

        List<Map<String, String>> arrayList = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("accountId","");
        map1.put("orderId","");
        map1.put("txAmount","");
        map1.put("txFee","");
        map1.put("tsfAmount","");
        map1.put("forAccountId","");
        map1.put("orgOrderId","");
        map1.put("orgTxAmount","");
        map1.put("productId","");
        map1.put("contOrderId","");
        arrayList.add(map1);

        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "batchCreditInvest");

        reqMap.put("batchNo","111111");
        reqMap.put("txAmount","100");
        reqMap.put("txCounts","12");
        reqMap.put("notifyURL","");
        reqMap.put("retNotifyURL","");
        reqMap.put("acqRes","");
        reqMap.put("subPacks",arrayList.toString());

        testCommon(reqMap);
    }

    /**
     * 批次担保账户代偿
     * 功能说明：通过担保账户向投资人还款，实际生效的时间视银行处理情况而定，支持多笔交易，同一个批次号的交易一起处理，
     *        但是可能仅部分交易成功。后台收到请求以后，同步回应接收结果，异步通知请求方报文收取和合法性判断的结果
     *        （P2P平台收到后回应success表示收到异步通知），业务处理也异步通知到相应的URL（P2P平台收到后回
     *        应success表示收到异步通知），或者请求方可以主动查询。
     * @throws Exception
     */
    
    public void testBatchBailRepay() throws Exception{

        List<Map<String, String>> arrayList = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("orderId","");
        map1.put("txAmount","");
        map1.put("txCapAmout","");
        map1.put("txIntAmount","");
        map1.put("forAccountId","");
        map1.put("orgOrderId","");
        map1.put("orgTxAmount","");
        arrayList.add(map1);

        Map<String, String> reqMap =new TreeMap<>();
        getHeadReq(reqMap);
        reqMap.put("txCode", "batchBailRepay");

        reqMap.put("batchNo","111111");
        reqMap.put("accountId","6212461270000004719");
        reqMap.put("productId","121111");
        reqMap.put("txAmount","100");
        reqMap.put("txCounts","10");
        reqMap.put("notifyURL","");
        reqMap.put("retNotifyURL","");
        reqMap.put("acqRes","");
        reqMap.put("subPacks",arrayList.toString());

        testCommon(reqMap);
    }

    /**
     * 通用部分统一管理
     * @param reqMap
     * @return
     */
    private Map<String, String> getHeadReq(Map<String, String> reqMap) {
        // TODO Auto-generated method stub

        reqMap.put("version", VERSION);
        reqMap.put("instCode", INSTCODE);
        reqMap.put("bankCode", BANKCODE);
        reqMap.put("txDate", DateUtil.getDate());
        reqMap.put("txTime", DateUtil.getTime());
        reqMap.put("seqNo", DateUtil.getRandomStr(6));
        reqMap.put("channel", COINSTCHANNEL);

        return reqMap;
    }

    /**
     * 组织参数发起请求
     * @param reqMap
     * @throws Exception
     */
    @SuppressWarnings({ "serial", "rawtypes", "unchecked" })
    public void testCommon(Map<String, String> reqMap) throws Exception{

        //生成待签名字符串
        String requestMapMerged = mergeMap(reqMap);
        //生成签名
//        String sign = SignUtil_lj.sign(requestMapMerged);
//
//        reqMap.put("sign", sign);
    }



    HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                    + session.getPeerHost());
            return true;
        }
    };

    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }

    static class miTM implements javax.net.ssl.TrustManager,
            javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

    }

    /**
     * 获取Map的待签名字符串
     * @param map
     * @return
     */
    public static String mergeMap(Map<String,String> map) {
        //字典序排序后生成待签名字符串
//        Map<String,String> reqMap = new TreeMap<String,String>(map);
        Map<String,String> reqMap = map;

        StringBuffer buff = new StringBuffer();

        Iterator<Map.Entry<String, String>> iter = reqMap.entrySet().iterator();
        Map.Entry<String, String> entry;
        while (iter.hasNext()) {
            entry = iter.next();
            if (!"sign".equals(entry.getKey())) {
                if(entry.getValue()==null){
                    entry.setValue("");
                    buff.append("");
                }else{
                    buff.append(String.valueOf(entry.getValue()));
                }
            }
        }

        String requestMerged = buff.toString();
        return requestMerged;
    }
}
