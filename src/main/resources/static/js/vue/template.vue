//ヘッダー表示
const pageTop = Vue.createApp({}); 
pageTop.component('page-top-vue', { 
template: `
 <div class="text-flex">
	<div class="title">
      <h1>家を建てる際の情報サイト</h1>
      </div>
      <div class="header-right">
        <a href="/">ホーム</a>
        <a href="/about">サイトについて</a>
        <a href="/contact">お問い合わせ</a>
</div>
</div>
` }); 
pageTop.mount('#pagetop');

//フッター表示
const pageEnd = Vue.createApp({}); 
pageEnd.component('page-end-vue', { 
template: `
 <div>
    <nav>
	<a href="/">ホーム</a> <a href="/about">サイトについて</a> <a href="/contact">お問い合わせ</a>
	</nav>
	<p>&copy; 2023 家を建てる際の情報サイト</p>
    </div>
` }); 
pageEnd.mount('#pageend');
