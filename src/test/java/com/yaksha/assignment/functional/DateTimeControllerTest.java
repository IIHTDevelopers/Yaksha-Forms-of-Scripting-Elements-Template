package com.yaksha.assignment.functional;

import static com.yaksha.assignment.utils.TestUtils.businessTestFile;
import static com.yaksha.assignment.utils.TestUtils.currentTest;
import static com.yaksha.assignment.utils.TestUtils.testReport;
import static com.yaksha.assignment.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yaksha.assignment.controller.DateTimeController;
import com.yaksha.assignment.utils.CustomParser;

public class DateTimeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testShowDateTimePageAsIndex() throws Exception {
		// Check if the @GetMapping("/") annotation is present on showDateTime method
		Method method = DateTimeController.class.getMethod("showDateTime", Model.class);
		GetMapping getMapping = method.getAnnotation(GetMapping.class);

		// Ensure the @GetMapping annotation exists with the correct path value
		boolean isGetMappingCorrect = getMapping != null && getMapping.value()[0].equals("/datetime");

		// Directly call the controller method
		DateTimeController controller = new DateTimeController();
		String viewName = controller.showDateTime(new org.springframework.ui.Model() {
			@Override
			public Model addAttribute(String attributeName, Object attributeValue) {
				return this;
			}

			@Override
			public Model addAllAttributes(java.util.Map<String, ?> attributes) {
				return this;
			}

			@Override
			public Model addAllAttributes(java.util.Collection<?> attributeValues) {
				return this;
			}

			@Override
			public boolean containsAttribute(String attributeName) {
				return false;
			}

			@Override
			public java.util.Map<String, Object> asMap() {
				return null;
			}

			@Override
			public Model addAttribute(Object attributeValue) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Model mergeAttributes(Map<String, ?> attributes) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getAttribute(String attributeName) {
				// TODO Auto-generated method stub
				return null;
			}
		});

		// Check if the returned view is "datetime"
		boolean isDateTimeViewReturned = "datetime".equals(viewName);

		// Output debugging information
		System.out.println("testShowDateTimePageAsIndex:");
		System.out.println("Is @GetMapping correct: " + isGetMappingCorrect);
		System.out.println("Is 'datetime' view returned: " + isDateTimeViewReturned);

		// Auto-grading with yakshaAssert
		yakshaAssert(currentTest(), isGetMappingCorrect && isDateTimeViewReturned, businessTestFile);
	}

	@Test
	public void testJspTagsAndHtmlTagClosureInIndexJsp() throws IOException {
		String filePath = "src/main/webapp/index.jsp";

		// Check for form submission and input elements in index.jsp
		boolean hasFormTag = CustomParser.checkJspTagPresence(filePath, "<form");
		boolean hasInputTags = CustomParser.checkJspTagPresence(filePath, "<input");

		// Run auto-grading using yakshaAssert
		yakshaAssert(currentTest(), hasFormTag && hasInputTags, businessTestFile);
	}

	@Test
	public void testJspTagsAndHtmlTagClosureInDateTimeJsp() throws IOException {
		String filePath = "src/main/webapp/WEB-INF/views/datetime.jsp";

		// Ensure the datetime.jsp page has scriptlet usage
		boolean hasScriptletTag = CustomParser.checkJspTagPresence(filePath, "<%");
		boolean hasExpressionTag = CustomParser.checkJspTagPresence(filePath, "${");
		boolean hasDeclarationTag = CustomParser.checkJspTagPresence(filePath, "<%! ");

		// Ensure that the page also properly closes HTML tags
		boolean hasFormTag = CustomParser.checkJspTagPresence(filePath, "<form");
		boolean hasInputTags = CustomParser.checkJspTagPresence(filePath, "<input");

		// Run auto-grading using yakshaAssert
		yakshaAssert(currentTest(),
				hasScriptletTag && hasExpressionTag && hasDeclarationTag && hasFormTag && hasInputTags,
				businessTestFile);
	}

	@Test
	public void testDateTimeControllerWithCorrectModel() throws Exception {
		// Create a mock Model
		Model model = new org.springframework.ui.Model() {
			private java.util.Map<String, Object> attributes = new java.util.HashMap<>();

			@Override
			public Model addAttribute(String attributeName, Object attributeValue) {
				attributes.put(attributeName, attributeValue);
				return this;
			}

			@Override
			public Model addAllAttributes(java.util.Collection<?> attributeValues) {
				return this;
			}

			@Override
			public Model addAllAttributes(java.util.Map<String, ?> attributes) {
				this.attributes.putAll(attributes);
				return this;
			}

			@Override
			public boolean containsAttribute(String attributeName) {
				return attributes.containsKey(attributeName);
			}

			@Override
			public java.util.Map<String, Object> asMap() {
				return attributes;
			}

			@Override
			public Model addAttribute(Object attributeValue) {
				return null;
			}

			@Override
			public Model mergeAttributes(java.util.Map<String, ?> attributes) {
				return this;
			}

			@Override
			public Object getAttribute(String attributeName) {
				return null;
			}
		};

		// Call the controller method
		DateTimeController controller = new DateTimeController();
		String viewName = controller.showDateTime(model);

		// Check if the model contains the correct date-time value and the returned view
		// is "datetime"
		boolean isDateTimeCorrect = model.asMap().containsKey("currentDateTime");
		boolean isDateTimeViewReturned = "datetime".equals(viewName);

		// Output debugging information
		System.out.println("testDateTimeControllerWithCorrectModel:");
		System.out.println("Is currentDateTime attribute present: " + isDateTimeCorrect);
		System.out.println("Is 'datetime' view returned: " + isDateTimeViewReturned);

		// Auto-grading with yakshaAssert
		yakshaAssert(currentTest(), isDateTimeCorrect && isDateTimeViewReturned, businessTestFile);
	}
}
