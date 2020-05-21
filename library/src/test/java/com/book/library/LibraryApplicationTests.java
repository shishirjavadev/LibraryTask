diff --git a/library/src/test/java/com/book/library/LibraryApplicationTests.java b/library/src/test/java/com/book/library/LibraryApplicationTests.java
index 7010123..2583b96 100644
--- a/library/src/test/java/com/book/library/LibraryApplicationTests.java
+++ b/library/src/test/java/com/book/library/LibraryApplicationTests.java
@@ -1,13 +1,45 @@
 package com.book.library;
 
+import com.library.book.model.Book;
 import org.junit.jupiter.api.Test;
-import org.springframework.boot.test.context.SpringBootTest;
+import org.junit.runner.RunWith;
+import org.springframework.http.MediaType;
+import org.springframework.test.context.junit4.SpringRunner;
+import org.springframework.test.web.servlet.MvcResult;
+import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
 
-@SpringBootTest
-class LibraryApplicationTests {
+import static org.junit.Assert.assertEquals;
+
+@RunWith(SpringRunner.class)
+class LibraryApplicationTests extends AbstractTest{
+
+/*    @Autowired
+    private MockMvc mockMvc;
+
+    @Autowired
+    private ObjectMapper objectMapper;
+
+    @Autowired
+    private BookRepository bookRepository;*/
 
     @Test
-    void contextLoads() {
+    public void createProduct() throws Exception {
+        String uri = "/api/v1/books/";
+        Book bookObj = new Book();
+
+        bookObj.setName("Book of Eli");
+        bookObj.setPublished("21-5-2020");
+        bookObj.setRating(5);
+
+        String inputJson = super.mapToJson(bookObj);
+        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
+                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
+
+        int status = mvcResult.getResponse().getStatus();
+        assertEquals(201, status);
+        String content = mvcResult.getResponse().getContentAsString();
+        System.out.println("content  "+content);
+        assertEquals(content, "Product is created successfully");
     }
 
 }
