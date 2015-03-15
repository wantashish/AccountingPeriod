package uk.co.agilelogics;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.mapper.ObjectMapper;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.path.json.JsonPath.from;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ashishsri
 * Date: 28/02/15
 * Time: 16:53
 * To change this template use File | Settings | File Templates.
 */
public class GetResourceDemo {
    public static void main(String[] args){
        RestAssured.baseURI = "https://hods-proxy-qa-right.tax.service.gov.uk";
        ArrayList<String> utrList = new ArrayList<String>();
        utrList.add("1777802586");
        utrList.add("6471604968");
        utrList.add("6437623340");
        utrList.add("5597800686");
        utrList.add("2915326396");
        utrList.add("1372613788");
        utrList.add("5711615847");
        utrList.add("5276623857");
        utrList.add("9653921991");
        utrList.add("5968510062");
        for(String utr:utrList){
            ArrayList<AccountingPeriod> accountingPeriods = getAccountingPeriods(utr);
            for(AccountingPeriod ap : accountingPeriods){
                System.out.println("UTR: "+utr+" Period: "+ap.getPeriod()+" Status: "+ap.getPeriodStatus()+
                        " Start date: "+ap.getPeriodStartDate()+" End date: "+ap.getPeriodEndDate());
            }
            System.out.println("---------------------------------------------------------------------");
        }
    }

    private static ArrayList<AccountingPeriod> getAccountingPeriods(String utr) {
        String accountingPeriodsURI = "/corporation-tax/companies/"+utr+"/accounting-periods";
        ArrayList<String> hrefList = new ArrayList<String>();
        ArrayList<AccountingPeriod> accountingPeriods = new ArrayList<AccountingPeriod>();
        hrefList = (ArrayList<String>)flattenList((List<?>)get(accountingPeriodsURI).getBody().path("accountPeriods.links.href"));
        for(String accountingPeriodURL : hrefList){
            AccountingPeriod ap = get(accountingPeriodURL).as(AccountingPeriod.class);
            ap.setPeriod(accountingPeriodURL.split("/")[accountingPeriodURL.split("/").length-1]);
            accountingPeriods.add(ap);
        }
        return accountingPeriods;
    }

    private static ArrayList<?> flattenList(List<?> inputList) {
        ArrayList<Object> flatList = new ArrayList<Object>();
        for(Object o : inputList){
            if(o instanceof List<?>){
                flatList.addAll(flattenList((List<?>)o));
            } else {
                flatList.add(o);
            }

        }
        return flatList;
    }
}
