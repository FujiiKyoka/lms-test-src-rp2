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
 * ケース05
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース05 キーワード検索 正常系")
public class Case05 {

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

	@Test
	@Order(5)
	@DisplayName("テスト05 キーワード検索で該当キーワードを含む検索結果だけ表示")
	void test05() {
		//キーワード欄へ「キャンセル料」と入力して検索を押下
		webDriver.findElement(By.name("keyword")).sendKeys("キャンセル料");
		webDriver.findElement(By.cssSelector("input[type='submit']")).click();

		// 画面スクロール
		scrollBy("document.body.scrollHeight");
		// 読み込み待機
		pageLoadTimeout(5);

		assertTrue(webDriver.getPageSource().contains("キャンセル料"));
		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 「クリア」ボタン押下で入力したキーワードを消去")
	void test06() {
		//クリアボタンを押下後、キーワード欄が空白になっていることを確認
		webDriver.findElement(By.cssSelector("input[type='button']")).click();
		assertEquals("", webDriver.findElement(By.name("keyword")).getAttribute("value"));

		// エビデンス
		getEvidence(new Object() {
		});
	}

}
