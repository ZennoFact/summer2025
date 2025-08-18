# SummerCourse 2025 -Graphics2Dで遊ぶJava-
授業資料として準備したリポジトリです。どこまで作りこめるかは私の気合いと元気次第。説明を聞いて実習という点ももちろんありますが，他の授業に比べてまず触ってみましょうという感覚の強い授業になりそうです。

## やること
- Javaのクラスと継承（と場合によってはインタフェース）を再確認
- Swingの使い方
- 画面上に描画をする
- その他，豆知識

## やらないこと
- 変数，型，条件分岐，反復処理，配列といったことは丁寧に振り替えることはしません
- GUIのアプリケーションとしての開発（Java実習2やJavaアプリケーション開発でやります）
- テスト

## Eclipse

[ここ](https://willbrains.jp/)からダウンロードを。
PleiadesはUIを日本語化するためのパッケージなのだけど，日本語化したUIだとキーボードショートカットが著しく使いにくくなるので，私は表面を英語表記に戻しています。

### 英語表記にする方法

Plaiadesのフォルダ内にEclipseフォルダがあるが，その中の「eclipse.ini」を編集。
 - -javaagent:dropins/MergeDoc/eclipse/plugins/jp.sourceforge.mergedoc.pleiades/pleiades.jar　の行を削除or#でコメントアウト
 - -Duser.language=en_US を最終行に追記
上記の修正後，Eclipseの再起動でうまくいくはず，です。

