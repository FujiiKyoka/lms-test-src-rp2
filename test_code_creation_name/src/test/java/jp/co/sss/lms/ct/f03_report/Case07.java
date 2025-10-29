package jp.co.sss.lms.ct.f03_report;

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
import org.openqa.selenium.WebElement;

/**
 * 結合テスト レポート機能
 * ケース07
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース07 受講生 レポート新規登録(日報) 正常系")
public class Case07 {

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
	@DisplayName("テスト03 未提出の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// レポートが未提出の日の詳細を押下
		WebElement none = webDriver.findElement(By.xpath("//tr[td/span[normalize-space()='未提出']]"));
		none.findElement(By.xpath(".//input[@value='詳細']")).click();

		// セクション詳細画面に遷移
		assertEquals("http://localhost:8080/lms/section/detail", webDriver.getCurrentUrl());
		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「提出する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// 「日報【デモ】を提出する」ボタンを押下
		webDriver.findElement(By.cssSelector("input[type='submit'][value='日報【デモ】を提出する']")).click();
		// 日報登録画面に遷移
		assertEquals("http://localhost:8080/lms/report/regist", webDriver.getCurrentUrl());
		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を入力して「提出する」ボタンを押下し確認ボタン名が更新される")
	void test05() {
		// 日報へ「日報を提出する」と記入し提出ボタンを押下
		WebElement input = webDriver.findElement(By.tagName("textarea"));
		input.sendKeys("日報を提出する");
		webDriver.findElement(By.className("btn-primary")).click();

		assertEquals("提出済み日報【デモ】を確認する",
				webDriver.findElement(By.cssSelector("input[type='submit']")).getAttribute("value"));

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

}
