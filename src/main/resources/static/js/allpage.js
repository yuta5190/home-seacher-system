//ぺージ最上部
Vue.component("page-top", {
  template: `
    <div>
      <div class="header-right">
        <a href="/">ホーム</a>
        <a href="/about">サイトについて</a>
        <a href="/contact">お問い合わせ</a>
      </div>
      <h1>家を建てる際の情報サイト</h1>
    </div>
  `,
});

const pagetop = new Vue({
	el:"#pagetop",
	
});

Vue.component("page-end", {
  template: `
    <div>
    <nav>
	<a href="/">ホーム</a> <a href="/about">サイトについて</a> <a href="/contact">お問い合わせ</a>
	</nav>
	<p>&copy; 2023 家を建てる際の情報サイト</p>
    </div>
  `,
});

const pageend = new Vue({
	el:"#pageend",
	
});
