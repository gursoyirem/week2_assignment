# DevCluster

**Android Donanım ve Kaynak Yönetimi Simülatörü**

DevCluster, bir geliştirme ekibinin kullanabileceği donanımları listelemeyi, aramayı, kategoriye göre filtrelemeyi ve örnek bir satın alma sürecini simüle etmeyi amaçlayan bir Android uygulamasıdır. Kotlin ve Jetpack Compose ile geliştirilmiştir.

> Bu proje bir eğitim/ödev çalışmasıdır. Satın alma işlemleri ve stok değişiklikleri simülasyondur; gerçek ödeme yapılmaz.

## Özellikler

- **Donanım listesi:** Ürün adı, kategori, birim maliyet ve kalan stok bilgilerini kartlar üzerinde gösterir.
- **Arama:** Donanımları ada veya kategori adına göre, büyük/küçük harf duyarlılığı olmadan arar.
- **Kategori filtreleme:** Yatay kategori seçenekleriyle listeyi daraltır; **Tümü** seçeneğiyle bütün ürünleri gösterir.
- **Satın alma simülasyonu:** Stok bulunan bir karta dokunulduğunda ürünün kalan stokunu bir azaltır ve maliyetini toplam harcamaya ekler.
- **Stok kontrolü:** Stoku tükenen ürünlerin tekrar seçilmesini engeller ve **Stokta yok** mesajını gösterir.
- **Boş sonuç durumu:** Aramaya veya filtreye uyan ürün bulunamadığında bilgilendirme gösterir.

## Kullanılan teknolojiler

- **Kotlin** — uygulama geliştirme dili
- **Jetpack Compose** — deklaratif kullanıcı arayüzü
- **Material 3** — arayüz bileşenleri ve tema
- **Gradle (Kotlin DSL)** — derleme ve bağımlılık yönetimi
- **Android Studio** — geliştirme ortamı

**Minimum Android sürümü:** API 24 (Android 7.0)

## Proje yapısı

```text
app/src/main/
├── AndroidManifest.xml
└── java/com/example/week2/
    ├── MainActivity.kt   # Ekran, arama, filtreleme ve satın alma etkileşimleri
    ├── HardwareItem.kt  # Donanım veri modeli
    └── HardwareData.kt  # Örnek donanım listesi
```

## Kurulum ve çalıştırma

1. Bu depoyu bilgisayarınıza klonlayın:

   ```bash
   git clone https://github.com/gursoyirem/week2_assignment.git
   ```

2. **Android Studio** üzerinden proje klasörünü açın.
3. Gradle senkronizasyonunun tamamlanmasını bekleyin. Gerekli Android SDK bileşenleri istenirse kurun.
4. Bir Android emülatörü veya USB hata ayıklaması etkin fiziksel cihaz seçin.
5. **Run 'app'** ile uygulamayı başlatın.

## Kullanım

1. Ana ekrandaki donanım kartlarını inceleyin.
2. **Donanım ara** alanından ürün veya kategori adıyla arama yapın.
3. Kategori seçeneklerinden biriyle listeyi filtreleyin.
4. Stok bulunan bir ürün kartına dokunarak satın alma işlemini simüle edin.
5. **Toplam harcama** ve **kalan stok** değerlerinin değişimini gözlemleyin.

## Kapsam ve sınırlamalar

- Donanım verileri uygulama içinde tanımlanmış **örnek verilerdir**; sunucudan veya veritabanından alınmaz.
- Satın alma/harcama ve stok değişiklikleri uygulamanın belleğinde tutulur; uygulama yeniden başlatıldığında kalıcı olarak korunmaz.
- Bu sürümde kullanıcı hesabı, gerçek ödeme, sunucu bağlantısı veya kalıcı envanter yönetimi bulunmaz.

## Geliştirici

**İrem Gürsoy**  
GitHub: [@gursoyirem](https://github.com/gursoyirem)

*Week 2 Android assignment*
