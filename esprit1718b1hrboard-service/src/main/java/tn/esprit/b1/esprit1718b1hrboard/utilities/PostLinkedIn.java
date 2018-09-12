package tn.esprit.b1.esprit1718b1hrboard.utilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.omg.PortableServer.ServantActivator;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PostLinkedIn {

	private static String API_KEY = "77ijzhwbuz72ug";
	private static String API_SECRET = "uj2lqXT4lfbiME8K";
	private static String CALL = "https://www.google.tn/";

	// public static void main(String[] args) {
	// Token accessToken = null;
	//
	// OAuthService service = new
	// ServiceBuilder().provider(LinkedInApi.class).apiKey(API_KEY).apiSecret(API_SECRET)
	// .callback(CALL).build();
	//
	// /*************************************
	// * This first piece of code handles all the pieces needed to be granted
	// * access to make a data call
	// */
	//
	// try {
	// File file = new File("service.dat");
	//
	// if (file.exists()) {
	// // if the file exists we assume it has the AuthHandler in it -
	// // which
	// // in turn contains the Access Token
	// ObjectInputStream inputStream = new ObjectInputStream(new
	// FileInputStream(file));
	// AuthHandler authHandler = (AuthHandler) inputStream.readObject();
	// accessToken = authHandler.getAccessToken();
	// } else {
	// System.out.println("There is no stored Access token we need to make
	// one");
	// // In the constructor the AuthHandler goes through the chain of
	// // calls to create an Access Token
	// AuthHandler authHandler = new AuthHandler(service);
	// ObjectOutputStream outputStream = new ObjectOutputStream(new
	// FileOutputStream("service.dat"));
	// outputStream.writeObject(authHandler);
	// outputStream.close();
	// accessToken = authHandler.getAccessToken();
	// System.out.println(accessToken);
	//
	// }
	//
	// } catch (Exception e) {
	// System.out.println("Threw an exception when serializing: " + e.getClass()
	// + " :: " + e.getMessage());
	// }
	//
	// System.out.println("********Write to the share and to twitter - using
	// JSON ********");
	// // This basic shares some basic information on the users activity stream
	// // https://developer.linkedin.com/documents/share-api
	// // NOTE - a good troubleshooting step is to validate your JSON on
	// // jsonlint.org
	// String url = "http://api.linkedin.com/v1/people/~/shares";
	// OAuthRequest request = new OAuthRequest(Verb.POST, url);
	// // set the headers to the server knows what we are sending
	// request.addHeader("Content-Type", "application/json");
	// request.addHeader("x-li-format", "json");
	// // make the json payload using json-simple
	// Map<String, Object> jsonMap = new HashMap<String, Object>();
	// jsonMap.put("comment", "From package service");
	// JSONObject contentObject = new JSONObject();
	// contentObject.put("title", "ALL STARS HR_BOARD");
	// contentObject.put("submitted-url", "https://www.facebook.com/bamboohr/");
	// contentObject.put("submitted-image-url",
	// "http://press.linkedin.com/sites/all/themes/presslinkedin/images/LinkedIn_WebLogo_LowResExample.jpg");
	// jsonMap.put("content", contentObject);
	// JSONObject visibilityObject = new JSONObject();
	// visibilityObject.put("code", "anyone");
	// jsonMap.put("visibility", visibilityObject);
	// request.addPayload(JSONValue.toJSONString(jsonMap));
	// service.signRequest(accessToken, request);
	// Response response = request.send();
	// // again no body - just headers
	// System.out.println(response.getBody());
	// System.out.println(response.getHeaders().toString());
	// System.out.println();
	// System.out.println();
	//
	// }

	 public void postToMyLinkedIn(String post) {
//	public static void main(String[] args) {

		Thread th = new Thread() {

			public synchronized void run() {
				try {
					Token accessToken = null;

					OAuthService service = new ServiceBuilder().provider(LinkedInApi.class).apiKey(API_KEY)
							.apiSecret(API_SECRET).callback(CALL).build();

					/*************************************
					 * This first piece of code handles all the pieces needed to
					 * be granted access to make a data call
					 */

					try {
						File file = new File("service.dat");

						if (file.exists()) {
							// if the file exists we assume it has the
							// AuthHandler in it -
							// which
							// in turn contains the Access Token
							ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
							AuthHandler authHandler = (AuthHandler) inputStream.readObject();
							accessToken = authHandler.getAccessToken();
						} else {
							System.out.println("There is no stored Access token we need to make one");
							// In the constructor the AuthHandler goes through
							// the chain of
							// calls to create an Access Token
							AuthHandler authHandler = new AuthHandler(service);
							ObjectOutputStream outputStream = new ObjectOutputStream(
									new FileOutputStream("service.dat"));
							outputStream.writeObject(authHandler);
							outputStream.close();
							accessToken = authHandler.getAccessToken();
							System.out.println(accessToken);

						}

					} catch (Exception e) {
						System.out.println(
								"Threw an exception when serializing: " + e.getClass() + " :: " + e.getMessage());
					}

					System.out.println("********Write to the  share and to twitter - using JSON ********");
					// This basic shares some basic information on the users
					// activity stream
					// https://developer.linkedin.com/documents/share-api
					// NOTE - a good troubleshooting step is to validate your
					// JSON on
					// jsonlint.org
					String url = "http://api.linkedin.com/v1/people/~/shares";
					OAuthRequest request = new OAuthRequest(Verb.POST, url);
					// set the headers to the server knows what we are sending
					request.addHeader("Content-Type", "application/json");
					request.addHeader("x-li-format", "json");
					// make the json payload using json-simple
					Map<String, Object> jsonMap = new HashMap<String, Object>();
					jsonMap.put("comment", "from services");
					JSONObject contentObject = new JSONObject();
					contentObject.put("title", "ALL STARS HR_BOARD");
					contentObject.put("submitted-url", "https://www.facebook.com/bamboohr/");
					contentObject.put("submitted-image-url",
							"http://press.linkedin.com/sites/all/themes/presslinkedin/images/LinkedIn_WebLogo_LowResExample.jpg");
					jsonMap.put("content", contentObject);
					JSONObject visibilityObject = new JSONObject();
					visibilityObject.put("code", "anyone");
					jsonMap.put("visibility", visibilityObject);
					request.addPayload(JSONValue.toJSONString(jsonMap));
					service.signRequest(accessToken, request);
					Response response = request.send();
					// again no body - just headers
					System.out.println(response.getBody());
					System.out.println(response.getHeaders().toString());
					System.out.println();
					System.out.println();

				}

				catch (Exception e) {
					e.printStackTrace();
				}

			}

		};

		th.start();

		// Token accessToken = null;
		//
		// OAuthService service = new
		// ServiceBuilder().provider(LinkedInApi.class).apiKey(API_KEY).apiSecret(API_SECRET)
		// .callback(CALL).build();
		//
		// /*************************************
		// * This first piece of code handles all the pieces needed to be
		// granted
		// * access to make a data call
		// */
		//
		// try {
		// File file = new File("service.dat");
		//
		// if (file.exists()) {
		// // if the file exists we assume it has the AuthHandler in it -
		// // which
		// // in turn contains the Access Token
		// ObjectInputStream inputStream = new ObjectInputStream(new
		// FileInputStream(file));
		// AuthHandler authHandler = (AuthHandler) inputStream.readObject();
		// accessToken = authHandler.getAccessToken();
		// } else {
		// System.out.println("There is no stored Access token we need to make
		// one");
		// // In the constructor the AuthHandler goes through the chain of
		// // calls to create an Access Token
		// AuthHandler authHandler = new AuthHandler(service);
		// ObjectOutputStream outputStream = new ObjectOutputStream(new
		// FileOutputStream("service.dat"));
		// outputStream.writeObject(authHandler);
		// outputStream.close();
		// accessToken = authHandler.getAccessToken();
		// System.out.println(accessToken);
		//
		// }
		//
		// } catch (Exception e) {
		// System.out.println("Threw an exception when serializing: " +
		// e.getClass() + " :: " + e.getMessage());
		// }
		//
		// System.out.println("********Write to the share and to twitter - using
		// JSON ********");
		// // This basic shares some basic information on the users activity
		// stream
		// // https://developer.linkedin.com/documents/share-api
		// // NOTE - a good troubleshooting step is to validate your JSON on
		// // jsonlint.org
		// String url = "http://api.linkedin.com/v1/people/~/shares";
		// OAuthRequest request = new OAuthRequest(Verb.POST, url);
		// // set the headers to the server knows what we are sending
		// request.addHeader("Content-Type", "application/json");
		// request.addHeader("x-li-format", "json");
		// // make the json payload using json-simple
		// Map<String, Object> jsonMap = new HashMap<String, Object>();
		// jsonMap.put("comment", post);
		// JSONObject contentObject = new JSONObject();
		// contentObject.put("title", "ALL STARS HR_BOARD");
		// contentObject.put("submitted-url",
		// "https://www.facebook.com/bamboohr/");
		// contentObject.put("submitted-image-url",
		// "http://press.linkedin.com/sites/all/themes/presslinkedin/images/LinkedIn_WebLogo_LowResExample.jpg");
		// jsonMap.put("content", contentObject);
		// JSONObject visibilityObject = new JSONObject();
		// visibilityObject.put("code", "anyone");
		// jsonMap.put("visibility", visibilityObject);
		// request.addPayload(JSONValue.toJSONString(jsonMap));
		// service.signRequest(accessToken, request);
		// Response response = request.send();
		// // again no body - just headers
		// System.out.println(response.getBody());
		// System.out.println(response.getHeaders().toString());
		// System.out.println();
		// System.out.println();

	}

}
