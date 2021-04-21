# SandMountainModel
砂山モデルをProcessingで可視化するコードです．

**SandMountainModel**ディレクトリをProcessingで開くと実行できます．

JVMバージョン：1.8

!["demo"](picture/SandModel_Animation.gif)

## ディレクトリ構成

!["directory"](picture/DirectoryHierarchy.png)

### 各ディレクトリの概説

- SandMountainModel：上記デモのプログラムが格納されています
- src：砂山モデルの中核処理のファイルが格納されています
- test：srcのテストディレクトリです
- test_case：testディレクトリのテストコードの決定表が格納されています


## 砂山モデルとは

(引用元：https://mas.kke.co.jp/model/sand_model/)

> 中身はブロック崩し  
> 地震や大災害や強行といった大災害が起こる仕組みを教えてくれる

## 砂山モデルのアルゴリズム

(引用元：https://mas.kke.co.jp/model/sand_model/)

!["algorithm"](picture/BTWmodel%20rule.jpg)

> 1. マス目の広がっている空間において，適当なマスに，一定時間ごとにブロックが積まれる．
> 2. ブロックが一定数（今回の場合は4）積まれると，そのマスのブロック数を0にし，そのマスの周囲にブロックが1つ積まれる．
> 3. ブロック数が4のマスがなくなるまで2を繰り返す．

本プログラムは，ブロックの積み重なりをブロックの白さで表しています．