package appiumIOS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.SSLSocketFactory;

















//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.*;
//import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.util.EntityUtils;
import org.jboss.netty.handler.codec.http.HttpMessage;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.openqa.jetty.util.URI;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import page.dashborad;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.json.*;
public class responseURL extends dashborad{
//static String  = "mobile.grocery.com";
static String baseURL = "http://mobile.tesco.com";
public static String loginUrl = "https://mobile.tesco.com";
public Object access_token;
public Object token_type;
public Object status_code;
public Object status_info;
public String slot;
public String orderNumber;
public Object profile_state;
//public static Object ProductId;
public static int FIRST_OBJ=0;
public static int weekNumber=2;

@SuppressWarnings("deprecation")
/*Creating global instance of HTTPCLIENT*/
HttpClient client = new DefaultHttpClient();

/*Default constructor*/
public responseURL(RemoteWebDriver driver) {
	super(driver);
}

/*constructor to set login response parameters*/
public void loginToken(Object access_token, Object token_type, Object status_code, Object status_info ){
	this.access_token = access_token;
	this.token_type = token_type;
	this.status_code = status_code;
	this.status_info = status_info;
}

/*Profile state constructor*/
public void profile(Object profile_state){
	this.profile_state = profile_state;
}

public void slot_id(String slot){
	this.slot = slot;
}

public void listOrders(String orderNumber){
	this.orderNumber = orderNumber;
}
/*Provides access token */
@SuppressWarnings("deprecation")
public void loginAccess() throws ClientProtocolException, IOException, org.apache.http.ParseException, ParseException, JSONException, URISyntaxException, HttpException, InterruptedException{
	HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
	@SuppressWarnings("deprecation")
	DefaultHttpClient client = new DefaultHttpClient();
	@SuppressWarnings("deprecation")
	SchemeRegistry registry = new SchemeRegistry();
	@SuppressWarnings("deprecation")
	SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getSocketFactory();
	((org.apache.http.conn.ssl.SSLSocketFactory) socketFactory).setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
	registry.register(new Scheme("https", socketFactory, 443));
	SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);
	HttpEntity input = new StringEntity("grant_type=password&Client_Secret=da6CF8PP8xni65eudmEX&Client_ID=AB37EB44DF1AC07531E5&version=2.0&username=sam%40t.com&password=Tesco123_");
	  HttpPost post = new HttpPost(loginUrl+"/groceryapi/RESTService.aspx?command=token&Version=2.0");
	  post.setEntity(input);
	  post.setHeader("Content-Type","application/x-www-form-urlencoded");
	  org.apache.http.HttpResponse response = client.execute(post);
	  HttpEntity entity =  response.getEntity();
      JSONObject jsonObject = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));
	  //JSONObject jsonObject = new JSONParser().parse(EntityUtils.toString(entity));
	  this.loginToken(jsonObject.get("access_token"), jsonObject.get((String) token_type), jsonObject.get((String) status_code), jsonObject.get((String) status_info));
      System.out.print(jsonObject.toString());;
}

/*Returns the items based on passed arguments*/
public void getSearchProducts(String searchString) throws ClientProtocolException, IOException, org.apache.http.ParseException, ParseException, JSONException, URISyntaxException, HttpException, InterruptedException
{
	  HttpGet request = new HttpGet(baseURL+"/groceryapi/RESTService.aspx?COMMAND=PRODUCTSEARCH&searchtext="+searchString+"&page=1&sessionkey="+access_token+"&version=2.0");
	  org.apache.http.HttpResponse response = client.execute(request);
	  HttpEntity entity = ((org.apache.http.HttpResponse) response).getEntity();
	  JSONObject obj = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));
	  System.out.print("\nsearched products are "+obj);
	}

/*Adds the passed argument items to basket */
public void addItemsWithCount(String product, int Count) throws ClientProtocolException, IOException, org.apache.http.ParseException, ParseException, URISyntaxException, HttpException, InterruptedException
{
	String ProductId = getProdID(product);
	HttpGet request = new HttpGet(baseURL+"/groceryapi/restservice.aspx?COMMAND=CHANGEBASKET&sessionkey="+access_token+"&PRODUCTID="+ProductId+"&CHANGEQUANTITY="+Count+"&version=2.0");
	org.apache.http.HttpResponse response = client.execute(request);
	 HttpEntity entity = ((org.apache.http.HttpResponse) response).getEntity();
	JSONObject obj = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));
	System.out.print("\nadded the items"+obj);
}
/*returns the ProductId for specified product*/
public String getProdID(String searchString) throws ClientProtocolException, IOException, org.apache.http.ParseException, ParseException, URISyntaxException, HttpException, InterruptedException
{
	  HttpGet request = new HttpGet(baseURL+"/groceryapi/RESTService.aspx?COMMAND=PRODUCTSEARCH&searchtext="+searchString+"&page=1&sessionkey="+access_token+"&version=2.0");
	  org.apache.http.HttpResponse response = client.execute(request);
	  HttpEntity entity = ((org.apache.http.HttpResponse) response).getEntity();
	  JSONObject obj = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));
	  
	  /*Get product Array from received JSON */
	  JSONArray getArray = (JSONArray) obj.get("Products");
	  
	  /*Get the first array block*/
	  JSONObject object = (JSONObject) getArray.get(FIRST_OBJ);
	  System.out.print(object.get("ProductId"));
	  return (String) object.get("ProductId");
}

/*Gets the amend profile state*/
public void profileState() throws ClientProtocolException, IOException, URISyntaxException, HttpException, InterruptedException
{
	HttpGet request = new HttpGet(baseURL+"/groceryapi/RESTService.aspx?COMMAND=LISTBASKETSUMMARY&sessionkey="+access_token+"&version=2.0");
	org.apache.http.HttpResponse response = client.execute(request);
	HttpEntity entity = ((org.apache.http.HttpResponse) response).getEntity();	this.profile(entity);
	System.out.print(entity);
}

/*Empty Basket*/
public void emptyBasket() throws ClientProtocolException, IOException, org.apache.http.ParseException, ParseException, URISyntaxException, HttpException, InterruptedException
{
	HttpGet request = new HttpGet(baseURL+"/groceryapi/RESTService.aspx?COMMAND=EMPTYBASKET&sessionkey="+access_token+"&version=2.0");
	org.apache.http.HttpResponse response = client.execute(request);
	HttpEntity entity = ((org.apache.http.HttpResponse) response).getEntity();
	JSONObject obj = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));
	System.out.print("\nBasket is empty."+obj);
}

/*Get the available slots*/
public void getAvailableSlotHD() throws ClientProtocolException, IOException, org.apache.http.ParseException, ParseException
{
	HttpGet request = new HttpGet(baseURL+"/groceryapi/RESTService.aspx?COMMAND=LISTDELIVERYSLOTS&SlotTypeID=OneHour&version=2.0&WEEKNO="+weekNumber+"&FORMATTEDDATES=Y&sessionkey="+access_token);
	org.apache.http.HttpResponse response = client.execute(request);
	HttpEntity entity = ((org.apache.http.HttpResponse) response).getEntity();
	JSONObject obj = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));
	
	/*Get the array items*/
	  JSONArray getArray = (JSONArray) obj.get("DeliverySlots");
	  System.out.print(getArray);
	/*Get the first slot id from the array*/
	  JSONObject object = (JSONObject) getArray.get(FIRST_OBJ);
	  String slot = (String) object.get("DeliverySlotId");
	  System.out.print("\nSelected slot is "+slot);
      this.slot_id(slot);
}

/*Choose the selected delivery slot*/
public void chooseDeliverySlot() throws ClientProtocolException, org.apache.http.ParseException, IOException, ParseException
{
	/*Get the slot ID*/
	HttpGet request = new HttpGet(baseURL+"/groceryapi/RESTService.aspx?COMMAND=CHOOSEDELIVERYSLOT&DELIVERYSLOTID="+URLEncoder.encode(slot, "UTF-8")+"&SlotTypeID=OneHour&version=2.0&sessionkey="+access_token);
	org.apache.http.HttpResponse response = client.execute(request);
	HttpEntity entity = ((org.apache.http.HttpResponse) response).getEntity();
	JSONObject obj = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));
    System.out.print("\nChoosen slot is "+obj);	
}

/*Amending the available orders*/
public void amendOrder() throws org.apache.http.ParseException, ParseException, IOException
{
	HttpGet request = new HttpGet(baseURL+"/groceryapi/RESTService.aspx?COMMAND=LISTPENDINGORDERS&sessionkey="+access_token+"&version=2.0");
	org.apache.http.HttpResponse response = client.execute(request);
	HttpEntity entity = ((org.apache.http.HttpResponse) response).getEntity();
	JSONObject obj = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));
	
	/*Get the array items*/
	  JSONArray getArray = (JSONArray) obj.get("PendingOrders");
	if (getArray == null){
		  System.out.print("\nNo Pending orders available");
	} else
	 {
	  System.out.print("\nAvailable pending orders are "+getArray);
	  JSONObject object = (JSONObject) getArray.get(FIRST_OBJ);
	  String orderNumber = (String) object.get("OrderNumber");
	  this.listOrders(orderNumber);
	  /*Fire amendOrderCall for the order number*/
	  HttpGet requestAmend = new HttpGet(baseURL+"/groceryapi/RESTService.aspx?COMMAND=AMENDORDER&ORDERNUMBER="+orderNumber+"&version=2.0&SURCHARGEMSG=Y&sessionkey="+access_token);
	  org.apache.http.HttpResponse responseAmend = client.execute(requestAmend);
	  HttpEntity entityAmend = ((org.apache.http.HttpResponse) responseAmend).getEntity();
	  JSONObject objAmend = (JSONObject) new JSONParser().parse(EntityUtils.toString(entityAmend));	 
	  System.out.print("\nOrder is amended. Details are "+objAmend);
      }
}
/*Cancel Amend Order*/
public void cancelAmendMode() throws ClientProtocolException, IOException, org.apache.http.ParseException, ParseException{
	HttpGet request = new HttpGet(baseURL+"/groceryapi/RESTService.aspx?COMMAND=CANCELAMENDORDER&version=2.0&sessionkey="+access_token);
	org.apache.http.HttpResponse response = client.execute(request);
	HttpEntity entity = ((org.apache.http.HttpResponse) response).getEntity();
	JSONObject obj = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));
	System.out.print("\nOut of amend mode.Status details are "+obj);
}

/*Favourites call*/
public void favourites() throws ClientProtocolException, IOException, org.apache.http.ParseException, ParseException{
	HttpGet request = new HttpGet(baseURL+"/groceryapi/RESTService.aspx?COMMAND=LISTFAVOURITES&sessionkey="+access_token+"&page=1&version=2.0");
	org.apache.http.HttpResponse response = client.execute(request);
	HttpEntity entity = ((org.apache.http.HttpResponse) response).getEntity();
	JSONObject obj = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));
	Long totalFavourites = (Long) obj.get("TotalProductCount");
	System.out.print("\nTotal number of favourites are  "+totalFavourites);
	}
}

