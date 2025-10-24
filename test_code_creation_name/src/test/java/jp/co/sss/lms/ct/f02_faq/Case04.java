package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		//ログイン画面にアクセス
		goTo("http://localhost:8080/lms");
		assertEquals("http://localhost:8080/lms/", webDriver.getCurrentUrl());
		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		//初回ログイン済みのユーザ情報を入力後ログイン処理
		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA01");
		webDriver.findElement(By.id("password")).sendKeys("StudentAA0101");
		webDriver.findElement(By.cssSelector("input[type='submit']")).click();
		//コース詳細画面に遷移
		assertEquals("http://localhost:8080/lms/course/detail", webDriver.getCurrentUrl());
		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// メニューから「機能」を押下
		webDriver.findElement(By.linkText("機能")).click();
		// ドロップダウンメニュー内「ヘルプ」を押下
		webDriver.findElement(By.linkText("ヘルプ")).click();
		// ヘルプ画面に遷移
		assertEquals("http://localhost:8080/lms/help", webDriver.getCurrentUrl());
		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		//元画面を保持
		String helpTab = webDriver.getWindowHandle();
		// 「よくある質問」を押下
		webDriver.findElement(By.linkText("よくある質問")).click();

		//別タブに切り替え
		for (String handle : webDriver.getWindowHandles()) {
			if (!handle.equals(helpTab)) {
				webDriver.switchTo().window(handle);
				break;
			}
		}
		//よくある質問画面に遷移
		assertEquals("http://localhost:8080/lms/faq", webDriver.getCurrentUrl());
		// エビデンス取得
		getEvidence(new Object() {
		});

	}

}
