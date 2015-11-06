package com.textmagic.sdk;

import static com.textmagic.sdk.RequestMethod.DELETE;
import static com.textmagic.sdk.RequestMethod.GET;
import static com.textmagic.sdk.RequestMethod.POST;
import static com.textmagic.sdk.RequestMethod.PUT;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.textmagic.sdk.resource.instance.*;

public class RestClientTest extends BasicTest {

	@Test
	public void testEndpointSelectionTest() {
		client = new RestClient("user", "token");
		assertTrue(client.getApiUri().startsWith(RestClient.PRODUCTION_URI));
	}

	@Test
	public void testEndpointSelectionProduction() {
		String testURI = "https://api.test.api/";
		client = new RestClient("user", "token", "https://api.test.api/");
		assertEquals(testURI, client.getApiUri());
	}

	@Test
    public void testBulk() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		setMockResponse(GET, "bulks/1", parameters, "bulkRetrieve.json", 200);
        TMBulk b = client.getResource(TMBulk.class);
        try {
            b.get(1);
        } catch (final RestException e) {
            System.out.println(e.getErrorMessage());
            throw new RuntimeException(e);
        }
        assertEquals((Integer) 1, b.getId());
        assertEquals("c", b.getStatus());
        assertEquals((Integer) 10000, b.getItemsProcessed());
        assertEquals((Integer) 10000, b.getItemsTotal());
        assertEquals("test", b.getText());
        assertNotNull(b.getSession());
    }
	
	@Test
    public void testBulks() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "bulks", parameters, "bulkList.json", 200);
		TMBulkList bl = client.getResource(TMBulkList.class);		
		Iterator<TMBulk> iterator = bl.iterator();
		assertTrue(iterator.hasNext());
		TMBulk b = iterator.next();
		assertNotNull(b);
        assertEquals((Integer) 1, b.getId());
        assertEquals("c", b.getStatus());
        assertEquals((Integer) 10000, b.getItemsProcessed());
        assertEquals((Integer) 10000, b.getItemsTotal());
        assertEquals("test", b.getText());
        assertNotNull(b.getSession());
    }
	
	@Test
    public void testChats() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "chats", parameters, "chatList.json", 200);
		setMockResponse(GET, "chats/1234567890", parameters, "chatMessageList.json", 200);
		TMChatList cl = client.getResource(TMChatList.class);
		Iterator<TMChat> iterator = cl.iterator();
		assertTrue(iterator.hasNext());
		TMChat c = iterator.next();
		assertEquals((Integer) 1, c.getId());
		assertEquals("1234567890", c.getPhone());
        Iterator<TMChatMessage> messageIterator = c.getMessagesIterator();
        assertTrue(messageIterator.hasNext());
        TMChatMessage m = messageIterator.next();
        assertNotNull(m);
        assertEquals((Integer) 1, m.getId());
        assertEquals("o", m.getDirection());
        assertEquals("1234567890", m.getReceiver());
        assertEquals("1234567890", m.getSender());
        assertEquals("f", m.getStatus());
        assertEquals("TEST", m.getText());
    }
	
	@Test
    public void testLists() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "lists", parameters, "listList.json", 200);
		TMListList ll = client.getResource(TMListList.class);		
		Iterator<TMList> iterator = ll.iterator();
		assertTrue(iterator.hasNext());
		TMList l = iterator.next();
		assertNotNull(l);
		assertEquals((Integer) 1, l.getId());
		assertEquals("TEST", l.getName());
		assertEquals("TEST", l.getDescription());
        assertEquals((Integer) 1, l.getMembersCount());
        assertFalse(l.getShared());
    }
	
	@Test
    public void testListsSearch() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("ids", "1");
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "lists/search", parameters, "listList.json", 200);
		TMListList ll = client.getResource(TMListList.class, parameters);
		Iterator<TMList> iterator = ll.iterator();
		assertTrue(iterator.hasNext());
		TMList l = iterator.next();
		assertNotNull(l);
		assertEquals((Integer) 1, l.getId());
		assertEquals("TEST", l.getName());
		assertEquals("TEST", l.getDescription());
        assertEquals((Integer) 1, l.getMembersCount());
        assertFalse(l.getShared());
    }
	
	@Test
    public void testCreateList() throws Exception {
		Map<String, String> parametersPost = new HashMap<String, String>();
		parametersPost.put("name", "TEST");
		parametersPost.put("shared", "1");
		setMockResponse(POST, "lists", parametersPost, "listCreateUpdate.json", 201);
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "lists/1", parametersGet, "listRetrieve.json", 200);
		TMList l = client.getResource(TMList.class);
		l.setName("TEST");
		l.setShared(true);
		try {
		    l.createOrUpdate();
		} catch (final RestException e) {
		    System.out.println(e.getErrors());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, l.getId());
		assertEquals("TEST", l.getName());
		assertEquals("TEST", l.getDescription());
        assertEquals((Integer) 1, l.getMembersCount());
        assertFalse(l.getShared());
    }
	
	@Test
    public void testUpdateList() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "lists/1", parametersGet, "listRetrieve.json", 200);
		TMList l = client.getResource(TMList.class);
		try {
		    l.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, l.getId());
		assertEquals("TEST", l.getName());
		assertEquals("TEST", l.getDescription());
        assertEquals((Integer) 1, l.getMembersCount());
        assertFalse(l.getShared());
        Map<String, String> parametersPut = new HashMap<String, String>();
        parametersPut.put("name", "TEST");
        parametersPut.put("shared", "0");
        setMockResponse(PUT, "lists/1", parametersPut, "listCreateUpdate.json", 201);
		setMockResponse(GET, "lists/1", parametersGet, "listRetrieveUpdated.json", 200);
        l.setShared(false);
        try {
		    l.createOrUpdate();
		} catch (final RestException e) {
		    System.out.println(e.getErrors());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, l.getId());
		assertEquals("TEST1", l.getName());
		assertEquals("TEST1", l.getDescription());
        assertEquals((Integer) 1, l.getMembersCount());
        assertTrue(l.getShared());
    }
	
	@Test
    public void testCreateContact() throws Exception {
		Map<String, String> parametersPost = new HashMap<String, String>();
		parametersPost.put("firstName", "TEST");
		parametersPost.put("lastName", "TEST");
		parametersPost.put("phone", "1234567890");
		parametersPost.put("email", "TEST");
		parametersPost.put("companyName", "TEST");
		parametersPost.put("lists", "1");
		setMockResponse(POST, "contacts", parametersPost, "contactCreateUpdate.json", 201);
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "contacts/1", parametersGet, "contactRetrieve.json", 200);
		TMContact c = client.getResource(TMContact.class);
		c.setFirstName("TEST");
		c.setLastName("TEST");
		c.setPhone("1234567890");
		c.setEmail("TEST");
		c.setCompanyName("TEST");
		c.setLists(Arrays.asList(new Integer[] {1}));
		try {
		    c.createOrUpdate();
		} catch (final RestException e) {
		    System.out.println(e.getErrors());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, c.getId());
		assertEquals("TEST", c.getFirstName());
		assertEquals("TEST", c.getLastName());
		assertEquals("1234567890", c.getPhone());
		assertEquals("TEST", c.getEmail());
		assertEquals("TEST", c.getCompanyName());
    }
	
	@Test
	public void testUpdateListContacts() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "lists/1", parametersGet, "listRetrieve.json", 200);
		TMList l = client.getResource(TMList.class);
		try {
		    l.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		Map<String, String> parametersPut = new HashMap<String, String>();
		parametersPut.put("contacts", "1");
		setMockResponse(PUT, "lists/1/contacts", parametersPut, "listCreateUpdate.json", 201);
		assertTrue(l.addContactsToList(Arrays.asList(new Integer[] {1})));
	}
	
	@Test
	public void testDeleteListContacts() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "lists/1", parametersGet, "listRetrieve.json", 200);
		TMList l = client.getResource(TMList.class);
		try {
		    l.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		Map<String, String> parametersDelete = new HashMap<String, String>();
		parametersDelete.put("contacts", "1");
		setMockResponse(DELETE, "lists/1/contacts", parametersDelete, null, 201);
		assertTrue(l.removeContactsFromList(Arrays.asList(new Integer[] {1})));
	}
	
	@Test
	public void retrieveListContacts() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "lists/1", parametersGet, "listRetrieve.json", 200);
		TMList l = client.getResource(TMList.class);
		try {
		    l.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		Map<String, String> parametersList = new HashMap<String, String>();
		parametersList.put("page", "1");
		parametersList.put("limit", "10");
		setMockResponse(GET, "lists/1/contacts", parametersList, "contactList.json", 200);
		Iterator<TMContact> iterator = l.getContactsIterator();
		assertTrue(iterator.hasNext());
		TMContact c = iterator.next();
		assertNotNull(c);
		assertEquals((Integer) 1, c.getId());
		assertEquals("TEST", c.getFirstName());
		assertEquals("TEST", c.getLastName());
		assertEquals("1234567890", c.getPhone());
		assertEquals("TEST", c.getEmail());
		assertEquals("TEST", c.getCompanyName());
	}
	
	@Test
    public void testContacts() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "contacts", parameters, "contactList.json", 200);
		TMContactList cl = client.getResource(TMContactList.class, parameters);
		Iterator<TMContact> iterator = cl.iterator();
		assertTrue(iterator.hasNext());
		TMContact c = iterator.next();
		assertNotNull(c);
		assertEquals((Integer) 1, c.getId());
        assertEquals("TEST", c.getFirstName());
        assertEquals("TEST", c.getLastName());
        assertEquals("TEST", c.getCompanyName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("TEST", c.getEmail());
        assertTrue(c.getCustomFieldsIterator().hasNext());
    }
	
	@Test
    public void testContactsSearch() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("listId", "1");
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "contacts/search", parameters, "contactList.json", 200);
		TMContactList cl = client.getResource(TMContactList.class, parameters);
		Iterator<TMContact> iterator = cl.iterator();
		assertTrue(iterator.hasNext());
		TMContact c = iterator.next();
		assertNotNull(c);
		assertEquals((Integer) 1, c.getId());
        assertEquals("TEST", c.getFirstName());
        assertEquals("TEST", c.getLastName());
        assertEquals("TEST", c.getCompanyName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("TEST", c.getEmail());
        assertTrue(c.getCustomFieldsIterator().hasNext());
    }
	
	@Test
    public void testUpdateContact() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "contacts/1", parametersGet, "contactRetrieve.json", 200);
		TMContact c = client.getResource(TMContact.class);
		try {
		    c.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, c.getId());
		assertEquals("TEST", c.getFirstName());
		assertEquals("TEST", c.getLastName());
		assertEquals("1234567890", c.getPhone());
		assertEquals("TEST", c.getEmail());
		assertEquals("TEST", c.getCompanyName());
        Map<String, String> parametersPut = new HashMap<String, String>();
        parametersPut.put("firstName", "TEST1");
        parametersPut.put("lastName", "TEST1");
        parametersPut.put("phone", "1234567890");
        parametersPut.put("email", "TEST1");
        parametersPut.put("companyName", "TEST1");
        parametersPut.put("lists", "1");
        setMockResponse(PUT, "contacts/1", parametersPut, "contactCreateUpdate.json", 201);
		setMockResponse(GET, "contacts/1", parametersGet, "contactRetrieveUpdated.json", 200);
		c.setFirstName("TEST1");
		c.setLastName("TEST1");
		c.setPhone("1234567890");
		c.setEmail("TEST1");
		c.setCompanyName("TEST1");
		c.setLists(Arrays.asList(new Integer[] {1}));
		try {
		    c.createOrUpdate();
		} catch (final RestException e) {
		    System.out.println(e.getErrors());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, c.getId());
		assertEquals("TEST1", c.getFirstName());
		assertEquals("TEST1", c.getLastName());
		assertEquals("1234567890", c.getPhone());
		assertEquals("TEST1", c.getEmail());
		assertEquals("TEST1", c.getCompanyName());
    }
	
	@Test
    public void testCustomFields() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "customfields", parameters, "customfieldList.json", 200);
		TMCustomFieldList cfl = client.getResource(TMCustomFieldList.class);
		Iterator<TMCustomField> iterator = cfl.iterator();
		assertTrue(iterator.hasNext());
		TMCustomField c = iterator.next();
		assertNotNull(c);
		assertEquals((Integer) 1, c.getId());
        assertEquals("TEST", c.getName());
    }
	
	@Test
    public void testCreateCustomField() throws Exception {
		Map<String, String> parametersPost = new HashMap<String, String>();
		parametersPost.put("name", "TEST");
		setMockResponse(POST, "customfields", parametersPost, "customfieldCreateUpdate.json", 201);
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "customfields/1", parametersGet, "customfieldRetrieve.json", 200);
		TMCustomField c = client.getResource(TMCustomField.class);
		c.setName("TEST");
		try {
		    c.createOrUpdate();
		} catch (final RestException e) {
		    System.out.println(e.getErrors());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, c.getId());
		assertEquals("TEST", c.getName());
    }
	
	@Test
    public void testUpdateCustomField() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "customfields/1", parametersGet, "customfieldRetrieve.json", 200);
		TMCustomField c = client.getResource(TMCustomField.class);
		try {
		    c.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, c.getId());
		assertEquals("TEST", c.getName());
        Map<String, String> parametersPut = new HashMap<String, String>();
        parametersPut.put("name", "TEST1");
        setMockResponse(PUT, "customfields/1", parametersPut, "customfieldCreateUpdate.json", 201);
		setMockResponse(GET, "customfields/1", parametersGet, "customfieldRetrieveUpdated.json", 200);
        c.setName("TEST1");
        try {
		    c.createOrUpdate();
		} catch (final RestException e) {
		    System.out.println(e.getErrors());
		    throw new RuntimeException(e);
		}
        assertEquals((Integer) 1, c.getId());
		assertEquals("TEST1", c.getName());
    }
	
	@Test
    public void testUpdateCustomFieldValue() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "customfields/1", parametersGet, "customfieldRetrieve.json", 200);
		TMCustomField c = client.getResource(TMCustomField.class);
		try {
		    c.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		Map<String, String> parametersPut = new HashMap<String, String>();
        parametersPut.put("contactId", "1");
        parametersPut.put("value", "TEST");
        setMockResponse(PUT, "customfields/1/update", parametersPut, "customfieldCreateUpdate.json", 201);
		assertTrue(c.updateContactValue(1, "TEST"));
	}
	
	@Test
    public void testDeleteCustomField() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "customfields/1", parametersGet, "customfieldRetrieve.json", 200);
		TMCustomField c = client.getResource(TMCustomField.class);
		try {
		    c.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		Map<String, String> parametersDelete = new HashMap<String, String>();
		setMockResponse(DELETE, "customfields/1", parametersDelete, null, 201);
		try {
		    assertTrue(c.delete());
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
	}
	
	@Test
    public void testDeleteContact() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "contacts/1", parametersGet, "contactRetrieve.json", 200);
		TMContact c = client.getResource(TMContact.class);
		try {
		    c.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		Map<String, String> parametersDelete = new HashMap<String, String>();
		setMockResponse(DELETE, "contacts/1", parametersDelete, null, 201);
		try {
		    assertTrue(c.delete());
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
	}
	
	@Test
    public void testMessages() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "messages", parameters, "messageList.json", 200);
		TMMessageList ml = client.getResource(TMMessageList.class);
		Iterator<TMMessage> iterator = ml.iterator();
		assertTrue(iterator.hasNext());
		TMMessage m = iterator.next();
		assertNotNull(m);
		assertEquals((Integer) 1, m.getId());
        assertEquals("1234567890", m.getReceiver());
        assertEquals("TEST", m.getText());
        assertEquals("1234567890", m.getSender());
    }
	
	@Test
    public void testMessagesSearch() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("ids", "1");
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "messages/search", parameters, "messageList.json", 200);
		TMMessageList ml = client.getResource(TMMessageList.class, parameters);
		Iterator<TMMessage> iterator = ml.iterator();
		assertTrue(iterator.hasNext());
		TMMessage m = iterator.next();
		assertNotNull(m);
		assertEquals((Integer) 1, m.getId());
        assertEquals("1234567890", m.getReceiver());
        assertEquals("TEST", m.getText());
        assertEquals("1234567890", m.getSender());
    }
	
	@Test
    public void testMessagesPrice() throws Exception {
		Map<String, String> parametersPost = new HashMap<String, String>();
		parametersPost.put("text", "TEST");
		parametersPost.put("phones", "1234567890");
		setMockResponse(GET, "messages/price", parametersPost, "messagePrice.json", 201);
		TMNewMessage m = client.getResource(TMNewMessage.class);
		m.setText("TEST");
		m.setPhones(Arrays.asList(new String[] {"1234567890"}));
		try {
			assertEquals((Double) 0.5, m.getPrice());
		} catch (final RestException e) {
		    System.out.println(e.getErrors());
		    throw new RuntimeException(e);
		}
	}
	
	@Test
    public void testMessagesSend() throws Exception {
		Map<String, String> parametersPost = new HashMap<String, String>();
		parametersPost.put("text", "TEST");
		parametersPost.put("phones", "1234567890");
		setMockResponse(POST, "messages", parametersPost, "messageSend.json", 201);
		TMNewMessage m = client.getResource(TMNewMessage.class);
		m.setText("TEST");
		m.setPhones(Arrays.asList(new String[] {"1234567890"}));
		try {
			m.send();
		} catch (final RestException e) {
		    System.out.println(e.getErrors());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, m.getId());
	}
	
	@Test
    public void testSessions() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "sessions", parameters, "sessionList.json", 200);
		TMSessionList sl = client.getResource(TMSessionList.class);
		Iterator<TMSession> iterator = sl.iterator();
		assertTrue(iterator.hasNext());
		TMSession s = iterator.next();
		assertNotNull(s);
		assertEquals((Integer) 1, s.getId());
		assertEquals("TEST", s.getText());
		assertEquals("A", s.getSource());
		assertEquals((Double) 0.5, s.getPrice());
        assertEquals((Integer) 1, s.getNumbersCount());
    }
	
	@Test
    public void testSession() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		setMockResponse(GET, "sessions/1", parameters, "sessionRetrieve.json", 200);
        TMSession s = client.getResource(TMSession.class);
        try {
            s.get(1);
        } catch (final RestException e) {
            System.out.println(e.getErrorMessage());
            throw new RuntimeException(e);
        }
        assertEquals((Integer) 1, s.getId());
        assertEquals("TEST", s.getText());
		assertEquals("A", s.getSource());
		assertEquals((Double) 0.5, s.getPrice());
        assertEquals((Integer) 1, s.getNumbersCount());
    }
	
	@Test
    public void testMessage() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		setMockResponse(GET, "messages/1", parameters, "messageRetrieve.json", 200);
        TMMessage m = client.getResource(TMMessage.class);
        try {
            m.get(1);
        } catch (final RestException e) {
            System.out.println(e.getErrorMessage());
            throw new RuntimeException(e);
        }
        assertEquals((Integer) 1, m.getId());
        assertEquals("1234567890", m.getReceiver());
        assertEquals("TEST", m.getText());
        assertEquals("1234567890", m.getSender());
    }
	
	@Test
    public void testDeleteMessage() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "messages/1", parametersGet, "messageRetrieve.json", 200);
		TMMessage m = client.getResource(TMMessage.class);
		try {
		    m.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		Map<String, String> parametersDelete = new HashMap<String, String>();
		setMockResponse(DELETE, "messages/1", parametersDelete, null, 201);
		try {
		    assertTrue(m.delete());
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
	}
	
	@Test
    public void testDeleteSession() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "sessions/1", parametersGet, "sessionRetrieve.json", 200);
		TMSession s = client.getResource(TMSession.class);
		try {
		    s.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		Map<String, String> parametersDelete = new HashMap<String, String>();
		setMockResponse(DELETE, "sessions/1", parametersDelete, null, 201);
		try {
		    assertTrue(s.delete());
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
	}
	
	@Test
    public void testReplies() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "replies", parameters, "replyList.json", 200);
		TMReplyList rl = client.getResource(TMReplyList.class);
		Iterator<TMReply> iterator = rl.iterator();
		assertTrue(iterator.hasNext());
		TMReply r = iterator.next();
		assertNotNull(r);
		assertEquals((Integer) 1, r.getId());
		assertEquals("1234567890", r.getSender());
		assertEquals("TEST", r.getText());
		assertEquals("1234567890", r.getReceiver());
    }
	
	@Test
    public void testRepliesSearch() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("ids", "1");
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "replies/search", parameters, "replyList.json", 200);
		TMReplyList rl = client.getResource(TMReplyList.class, parameters);
		Iterator<TMReply> iterator = rl.iterator();
		assertTrue(iterator.hasNext());
		TMReply r = iterator.next();
		assertNotNull(r);
		assertEquals((Integer) 1, r.getId());
		assertEquals("1234567890", r.getSender());
		assertEquals("TEST", r.getText());
		assertEquals("1234567890", r.getReceiver());
    }
	
	@Test
    public void testReply() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		setMockResponse(GET, "replies/1", parameters, "replyRetrieve.json", 200);
        TMReply r = client.getResource(TMReply.class);
        try {
            r.get(1);
        } catch (final RestException e) {
            System.out.println(e.getErrorMessage());
            throw new RuntimeException(e);
        }
        assertEquals((Integer) 1, r.getId());
		assertEquals("1234567890", r.getSender());
		assertEquals("TEST", r.getText());
		assertEquals("1234567890", r.getReceiver());
    }
	
	@Test
    public void testDeleteReply() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "replies/1", parametersGet, "replyRetrieve.json", 200);
		TMReply r = client.getResource(TMReply.class);
		try {
		    r.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		Map<String, String> parametersDelete = new HashMap<String, String>();
		setMockResponse(DELETE, "replies/1", parametersDelete, null, 201);
		try {
		    assertTrue(r.delete());
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
	}
	
	@Test
    public void testSchedules() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "schedules", parameters, "scheduleList.json", 200);
		TMScheduleList sl = client.getResource(TMScheduleList.class);
		Iterator<TMSchedule> iterator = sl.iterator();
		assertTrue(iterator.hasNext());
		TMSchedule s = iterator.next();
		assertNotNull(s);
		assertEquals((Integer) 1, s.getId());
		assertNotNull(s.getSession());
    }
	
	@Test
    public void testSchedule() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		setMockResponse(GET, "schedules/1", parameters, "scheduleRetrieve.json", 200);
        TMSchedule s = client.getResource(TMSchedule.class);
        try {
            s.get(1);
        } catch (final RestException e) {
            System.out.println(e.getErrorMessage());
            throw new RuntimeException(e);
        }
        assertEquals((Integer) 1, s.getId());
		assertNotNull(s.getSession());
    }
	
	@Test
    public void testDeleteSchedule() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "schedules/1", parametersGet, "scheduleRetrieve.json", 200);
		TMSchedule s = client.getResource(TMSchedule.class);
		try {
		    s.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		Map<String, String> parametersDelete = new HashMap<String, String>();
		setMockResponse(DELETE, "schedules/1", parametersDelete, null, 201);
		try {
		    assertTrue(s.delete());
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
	}
	
	@Test
    public void testTemplates() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "templates", parameters, "templateList.json", 200);
		TMTemplateList tl = client.getResource(TMTemplateList.class);
		Iterator<TMTemplate> iterator = tl.iterator();
		assertTrue(iterator.hasNext());
		TMTemplate t = iterator.next();
		assertNotNull(t);
		assertEquals((Integer) 1, t.getId());
		assertEquals("TEST", t.getName());
		assertEquals("TEST", t.getContent());
    }
	
	@Test
    public void testTemplatesSearch() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("ids", "1");
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "templates/search", parameters, "templateList.json", 200);
		TMTemplateList tl = client.getResource(TMTemplateList.class, parameters);
		Iterator<TMTemplate> iterator = tl.iterator();
		assertTrue(iterator.hasNext());
		TMTemplate t = iterator.next();
		assertNotNull(t);
		assertEquals((Integer) 1, t.getId());
		assertEquals("TEST", t.getName());
		assertEquals("TEST", t.getContent());
    }
	
	@Test
    public void testCreateTemplate() throws Exception {
		Map<String, String> parametersPost = new HashMap<String, String>();
		parametersPost.put("name", "TEST");
		parametersPost.put("content", "TEST");
		setMockResponse(POST, "templates", parametersPost, "templateCreateUpdate.json", 201);
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "templates/1", parametersGet, "templateRetrieve.json", 200);
		TMTemplate t = client.getResource(TMTemplate.class);
		t.setName("TEST");
		t.setContent("TEST");
		try {
		    t.createOrUpdate();
		} catch (final RestException e) {
		    System.out.println(e.getErrors());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, t.getId());
		assertEquals("TEST", t.getName());
		assertEquals("TEST", t.getContent());
    }
	
	@Test
    public void testUpdateTemplate() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "templates/1", parametersGet, "templateRetrieve.json", 200);
		TMTemplate t = client.getResource(TMTemplate.class);
		try {
		    t.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, t.getId());
		assertEquals("TEST", t.getName());
		assertEquals("TEST", t.getContent());
        Map<String, String> parametersPut = new HashMap<String, String>();
        parametersPut.put("name", "TEST1");
        parametersPut.put("content", "TEST1");
        setMockResponse(PUT, "templates/1", parametersPut, "templateCreateUpdate.json", 201);
		setMockResponse(GET, "templates/1", parametersGet, "templateRetrieveUpdated.json", 200);
        t.setName("TEST1");
        t.setContent("TEST1");
        try {
		    t.createOrUpdate();
		} catch (final RestException e) {
		    System.out.println(e.getErrors());
		    throw new RuntimeException(e);
		}
        assertEquals((Integer) 1, t.getId());
		assertEquals("TEST1", t.getName());
		assertEquals("TEST1", t.getContent());
    }
	
	@Test
    public void testDeleteTemplate() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "templates/1", parametersGet, "templateRetrieve.json", 200);
		TMTemplate t = client.getResource(TMTemplate.class);
		try {
		    t.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		Map<String, String> parametersDelete = new HashMap<String, String>();
		setMockResponse(DELETE, "templates/1", parametersDelete, null, 201);
		try {
		    assertTrue(t.delete());
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
	}
	
	@Test
    public void testUnsubscribers() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "unsubscribers", parameters, "unsubscriberList.json", 200);
		TMUnsubscriberList ul = client.getResource(TMUnsubscriberList.class);
		Iterator<TMUnsubscriber> iterator = ul.iterator();
		assertTrue(iterator.hasNext());
		TMUnsubscriber u = iterator.next();
		assertNotNull(u);
		assertEquals((Integer) 1, u.getId());
		assertEquals("1234567890", u.getPhone());
		assertEquals("TEST", u.getFirstName());
		assertEquals("TEST", u.getLastName());
    }
	
	@Test
    public void testCreateUnsubscriber() throws Exception {
		Map<String, String> parametersPost = new HashMap<String, String>();
		parametersPost.put("phone", "1234567890");
		setMockResponse(POST, "unsubscribers", parametersPost, "unsubscriberCreateUpdate.json", 201);
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "unsubscribers/1", parametersGet, "unsubscriberRetrieve.json", 200);
		TMUnsubscriber u = client.getResource(TMUnsubscriber.class);
		u.setPhone("1234567890");
		try {
		    u.createOrUpdate();
		} catch (final RestException e) {
		    System.out.println(e.getErrors());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, u.getId());
		assertEquals("1234567890", u.getPhone());
		assertEquals("TEST", u.getFirstName());
		assertEquals("TEST", u.getLastName());
    }
	
	@Test
    public void testUpdateUser() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "user", parametersGet, "userRetrieve.json", 200);
		TMAccount a = client.getResource(TMAccount.class);
		try {
		    a.get();
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, a.getId());
		assertEquals("TEST", a.getUsername());
		assertEquals("TEST", a.getFirstName());
		assertEquals("TEST", a.getLastName());
		assertEquals("A", a.getStatus());
		assertEquals((Double) 0.0, a.getBalance());
		assertEquals("TEST", a.getCompany());
		assertEquals("P", a.getSubaccountType());
        Map<String, String> parametersPut = new HashMap<String, String>();
        parametersPut.put("firstName", "TEST1");
        parametersPut.put("lastName", "TEST1");
        parametersPut.put("company", "TEST1");
        setMockResponse(PUT, "user", parametersPut, "userCreateUpdate.json", 201);
		setMockResponse(GET, "user", parametersGet, "userRetrieveUpdated.json", 200);
        a.setFirstName("TEST1");
        a.setLastName("TEST1");
        a.setCompany("TEST1");
        try {
		    a.update();
		} catch (final RestException e) {
		    System.out.println(e.getErrors());
		    throw new RuntimeException(e);
		}
        assertEquals((Integer) 1, a.getId());
        assertEquals("TEST1", a.getFirstName());
		assertEquals("TEST1", a.getLastName());
		assertEquals("TEST1", a.getCompany());
    }
	
	@Test
    public void testInvoices() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "invoices", parameters, "invoiceList.json", 200);
		TMInvoiceList il = client.getResource(TMInvoiceList.class);
		Iterator<TMInvoice> iterator = il.iterator();
		assertTrue(iterator.hasNext());
		TMInvoice i = iterator.next();
		assertNotNull(i);
		assertEquals((Integer) 1, i.getId());
		assertEquals((Integer) 1, i.getBundle());
		assertEquals("EUR", i.getCurrency());
		assertEquals((Double) 0.0, i.getVat());
		assertEquals("TEST", i.getPaymentMethod());
    }
	
	@Test
    public void testNumbers() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "numbers", parameters, "numberList.json", 200);
		TMNumberList nl = client.getResource(TMNumberList.class);
		Iterator<TMNumber> iterator = nl.iterator();
		assertTrue(iterator.hasNext());
		TMNumber n = iterator.next();
		assertNotNull(n);
		assertEquals((Integer) 1, n.getId());
		assertNotNull(n.getUser());
		assertEquals("1234567890", n.getPhone());
		assertEquals("GB", n.getCountry());
		assertEquals("A", n.getStatus());
	}
	
	@Test
    public void testAvailableNumbers() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("country", "GB");
		parameters.put("prefix", null);
		setMockResponse(GET, "numbers/available", parameters, "availableNumberList.json", 200);
		TMNumber n = client.getResource(TMNumber.class);
		List<String> nlist = null;
		try {
		    nlist = n.getAvailableNumbers("GB", null);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		};
		assertEquals("1234567890", nlist.get(0));
	}
	
	@Test
    public void testBuyNumber() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("phone", "1234567890");
		parameters.put("country", "GB");
		parameters.put("userId", "1");
		setMockResponse(POST, "numbers", parameters, "numberCreateUpdate.json", 201);
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "numbers/1", parametersGet, "numberRetrieve.json", 200);
		TMNumber n = client.getResource(TMNumber.class);
		n.setCountry("GB");
		n.setPhone("1234567890");
		n.setUserId(1);
		try {
		    n.createOrUpdate();
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, n.getId());
		assertNotNull(n.getUser());
		assertEquals("1234567890", n.getPhone());
		assertEquals("GB", n.getCountry());
		assertEquals("A", n.getStatus());
	}
	
	@Test
    public void testDeleteNumber() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "numbers/1", parametersGet, "numberRetrieve.json", 200);
		TMNumber n = client.getResource(TMNumber.class);
		try {
		    n.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		Map<String, String> parametersDelete = new HashMap<String, String>();
		setMockResponse(DELETE, "numbers/1", parametersDelete, null, 201);
		try {
		    assertTrue(n.delete());
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
	}
	
	@Test
    public void testSenderIds() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "senderids", parameters, "senderIdList.json", 200);
		TMSenderIdList sl = client.getResource(TMSenderIdList.class);
		Iterator<TMSenderId> iterator = sl.iterator();
		assertTrue(iterator.hasNext());
		TMSenderId s = iterator.next();
		assertNotNull(s);
		assertEquals((Integer) 1, s.getId());
		assertNotNull(s.getUser());
		assertEquals("TEST", s.getSenderId());
		assertEquals("A", s.getStatus());
	}
	
	@Test
    public void testCreateSenderId() throws Exception {
		Map<String, String> parametersPost = new HashMap<String, String>();
		parametersPost.put("senderId", "TEST");
		parametersPost.put("explanation", "TEST");
		setMockResponse(POST, "senderids", parametersPost, "senderIdCreateUpdate.json", 201);
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "senderids/1", parametersGet, "senderIdRetrieve.json", 200);
		TMSenderId s = client.getResource(TMSenderId.class);
		s.setSenderId("TEST");
		s.setExplanation("TEST");
		try {
		    s.createOrUpdate();
		} catch (final RestException e) {
		    System.out.println(e.getErrors());
		    throw new RuntimeException(e);
		}
		assertEquals((Integer) 1, s.getId());
		assertNotNull(s.getUser());
		assertEquals("TEST", s.getSenderId());
		assertEquals("A", s.getStatus());
    }
	
	@Test
    public void testDeleteSenderId() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "senderids/1", parametersGet, "senderIdRetrieve.json", 200);
		TMSenderId s = client.getResource(TMSenderId.class);
		try {
		    s.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		Map<String, String> parametersDelete = new HashMap<String, String>();
		setMockResponse(DELETE, "senderids/1", parametersDelete, null, 201);
		try {
		    assertTrue(s.delete());
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
	}
	
	@Test
    public void testSubaccounts() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("page", "1");
		parameters.put("limit", "10");
		setMockResponse(GET, "subaccounts", parameters, "subaccountList.json", 200);
		TMSubaccountList sl = client.getResource(TMSubaccountList.class);
		Iterator<TMSubaccount> iterator = sl.iterator();
		assertTrue(iterator.hasNext());
		TMSubaccount s = iterator.next();
		assertNotNull(s);
		assertEquals((Integer) 1, s.getId());
		assertEquals("TEST", s.getUsername());
		assertEquals("TEST", s.getFirstName());
		assertEquals("TEST", s.getLastName());
		assertEquals("A", s.getStatus());
		assertEquals((Double) 0.0, s.getBalance());
		assertEquals("TEST", s.getCompany());
		assertEquals("P", s.getSubaccountType());
	}
	
	@Test
    public void testCreateSubaccount() throws Exception {
		Map<String, String> parametersPost = new HashMap<String, String>();
		parametersPost.put("email", "TEST");
		parametersPost.put("role", "U");
		setMockResponse(POST, "subaccounts", parametersPost, null, 201);
		TMSubaccount s = client.getResource(TMSubaccount.class);
		s.setEmail("TEST");
		s.setRole("U");
		try {
		    assertTrue(s.createOrUpdate());
		} catch (final RestException e) {
		    System.out.println(e.getErrors());
		    throw new RuntimeException(e);
		}
    }
	
	@Test
    public void testDeleteSubaccount() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "subaccounts/1", parametersGet, "subaccountRetrieve.json", 200);
		TMSubaccount s = client.getResource(TMSubaccount.class);
		try {
		    s.get(1);
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
		Map<String, String> parametersDelete = new HashMap<String, String>();
		setMockResponse(DELETE, "subaccounts/1", parametersDelete, null, 201);
		try {
		    assertTrue(s.delete());
		} catch (final RestException e) {
		    System.out.println(e.getErrorMessage());
		    throw new RuntimeException(e);
		}
	}
	
	@Test
	@SuppressWarnings("rawtypes")
    public void testSources() throws Exception {
		Map<String, String> parametersGet = new HashMap<String, String>();
		setMockResponse(GET, "sources", parametersGet, "sourceList.json", 200);
		TMSourceList sl = client.getResource(TMSourceList.class);
		@SuppressWarnings("unchecked")
		List<String> dl = sl.getDedicatedNumbers();
		@SuppressWarnings("unchecked")
		List<String> il = sl.getSenderIds();
		@SuppressWarnings("unchecked")
		List<String> pl = sl.getUserPhones();
		@SuppressWarnings("unchecked")
		List<String> nl = sl.getSharedNumbers();
		assertEquals("1234567890", dl.get(0));
		assertEquals("TEST", il.get(0));
		assertEquals("1234567890", pl.get(0));
		assertEquals("1234567890", nl.get(0));
	}
	
	@Test
    public void testStatements() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("start", "7 days ago");
		parameters.put("end", "now");
		parameters.put("page", "1");
		parameters.put("limit", "10");	
		setMockResponse(GET, "stats/spending", parameters, "statementList.json", 200);
		TMStatementList sl = client.getResource(TMStatementList.class, parameters);
		Iterator<TMStatement> iterator = sl.iterator();
		assertTrue(iterator.hasNext());
		TMStatement s = iterator.next();
		assertNotNull(s);
		assertEquals((Integer) 1, s.getId());
		assertEquals(null, s.getUserId());
		assertEquals((Double) 0.5, s.getBalance());
		assertEquals((Double) 0.0, s.getDelta());
		assertEquals("sms", s.getType());
		assertEquals("1", s.getValue());
		assertEquals(null, s.getComment());
	}
	
	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public void testMessaging() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();	
		setMockResponse(GET, "stats/messaging", parameters, "messagingList.json", 200);
		TMMessagingList ml = client.getResource(TMMessagingList.class);
		Iterator<TMMessaging> iterator = ml.iterator();
		assertTrue(iterator.hasNext());
		TMMessaging m = iterator.next();
		assertNotNull(m);
		assertEquals((Double) 0.5, m.getReplyRate());
		assertEquals((Double) 0.5, m.getDeliveryRate());
		assertEquals((Double) 0.5, m.getCosts());
		assertEquals((Integer) 1, m.getMessagesReceived());
		assertEquals((Integer) 1, m.getMessagesSentDelivered());
		assertEquals((Integer) 1, m.getMessagesSentAccepted());
		assertEquals((Integer) 1, m.getMessagesSentBuffered());
		assertEquals((Integer) 1, m.getMessagesSentFailed());
		assertEquals((Integer) 1, m.getMessagesSentRejected());
		assertEquals((Integer) 1, m.getMessagesSentParts());
	}
}
