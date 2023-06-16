def sp() : Unit = {
    pers.setPosition(0, 0)
    kaktus1.setPosition(random(pers_x-100, pers_x+100), random(240, 260))
    kaktus2.setPosition(random(pers_x-100, pers_x+100), random(240, 260))
    kaktus3.setPosition(random(-240, -260), random(pers_y-100, pers_y+100))
    kaktus4.setPosition(random(-240, -260), random(pers_y-100, pers_y+100))
    kaktus5.setPosition(random(pers_x-100, pers_x+100), random(-240, -260))
    kaktus6.setPosition(random(pers_x-100, pers_x+100), random(-240, -260))
    kaktus7.setPosition(random(260, 240), random(pers_y-100, pers_y+100))
    kaktus8.setPosition(random(260, 240), random(pers_y-100, pers_y+100))
    zel1.setPosition(zel1_x, zel1_y)
}
def restart() : Unit = {
    println("TOPpoint : "+TOPpoint)
    ded = true
    speed = 5
    pers_x = 0
    pers_y = 0
    sp()
}
def zel1_funtion () : Unit = {
    var zelrandom = random(1,3)
    if (zelrandom == 1) {
        speed = superSpeed
    }
    else if (zelrandom == 2) {
        point += 1
        println("point : "+point)
    }
    zel1_x = random(-250, 250)
    zel1_y = random(-250, 250)
    zel1.setPosition(zel1_x, zel1_y)
}

clear
cleari
println("==-- создатели --==")
println("Михаилу Ушакову - программист и дизайнер")
println("Вова Уласевич - создатель идей")
println("Ване Денисову - программист")
println("Наш сайт PixelMania")
println("==-- --==")
println("Версия : 1_0_0 | Язык програмирования kojo")
println("Управление на клавеши :")
println("A - влево  D - вправо")
println("S - вниз   W - вверх")
println("R - рестарт")

var levelP = random(1, 100)
var level = levelP%2
var ded = true
var hp = 3
var point = 0
var TOPpoint = 0
var speed = 5
var superSpeed = 7
setBackground(ColorMaker.rgb(254, 185, 107))
if (level == 1) {
    setBackground(ColorMaker.rgb(26, 35, 126))
}
var log = Picture.image("log.png")
log.draw()
log.setPosition(-70, -70)
var z = readInt("скорость кактусов")
log.setPosition(0, 1000)
var zel1_x = random(-250, 250)
var zel1_y = random(-250, 250)
var zel1 = Picture.image("zel.png")
zel1.draw()
zel1.scale(0.35)
zel1.setPosition(zel1_x, zel1_y)

var pers_x = 0
var pers_y = 0
var pers = Picture.image("pers1.png")
if (level == 1) pers = Picture.image("pers2.png")
pers.draw()

var scallKaktus = 0.32
var kaktusTextur = "kaktus1.png"
if (level == 1) kaktusTextur = "kaktus2.png"

var kaktus1 = Picture.image(kaktusTextur)
var kaktus2 = Picture.image(kaktusTextur)
var kaktus3 = Picture.image(kaktusTextur)
var kaktus4 = Picture.image(kaktusTextur)
var kaktus5 = Picture.image(kaktusTextur)
var kaktus6 = Picture.image(kaktusTextur)
var kaktus7 = Picture.image(kaktusTextur)
var kaktus8 = Picture.image(kaktusTextur)
var kaktuss: Seq[Picture] = Seq(kaktus1, kaktus2, kaktus3, kaktus4, kaktus5, kaktus6, kaktus7, kaktus8)
for (kaktus <- kaktuss) {
    kaktus.draw()
    kaktus.scale(scallKaktus)
}

kaktus1.setPosition(random(pers_x-100, pers_x+100), random(240, 260))
kaktus1.rotate(180)
kaktus2.setPosition(random(pers_x-100, pers_x+100), random(240, 260))
kaktus2.rotate(180)

kaktus3.setPosition(random(-240, -260), random(pers_y-100, pers_y+100))
kaktus3.rotate(270)
kaktus4.setPosition(random(-240, -260), random(pers_y-100, pers_y+100))
kaktus4.rotate(270)

kaktus5.setPosition(random(pers_x-100, pers_x+100), random(-240, -260))
kaktus6.setPosition(random(pers_x-100, pers_x+100), random(-240, -260))


kaktus7.setPosition(random(260, 240), random(pers_y-100, pers_y+100))
kaktus7.rotate(90)
kaktus8.setPosition(random(260, 240), random(pers_y-100, pers_y+100))
kaktus8.rotate(90)

var pause = true
var ach1 = true
var ach2 = true
var ach3 = true

println("==-- --==")
println("hp : "+hp)

toggleFullScreenCanvas()

animate {
    if (isKeyPressed(Kc.VK_W)) pers.offset(0, speed)
    if (isKeyPressed(Kc.VK_S)) pers.offset(0, -speed)
    if (isKeyPressed(Kc.VK_D)) pers.offset(speed, 0)
    if (isKeyPressed(Kc.VK_A)) pers.offset(-speed, 0)
    if (isKeyPressed(Kc.VK_R)) {
        restart()
        if (ach3) {
            println ("ДОСТИЖЕНИЕ: Мирный Перезапуск (перезапустите игру)")
            ach3 = false
        }
        println("==-- --==")
    }
    if (pers_x >= 300) {
        pers_x *= (-1)
    }
    if (pers_x <= -300) {
        pers_x *= (-1)
    }
    if (pers_y >= 310) {
        pers_y *= (-1)
    }
    if (pers_y <= -310) {
        pers_y *= (-1)
    }
    if (kaktus1.position.y <= -300) {
        kaktus1.setPosition(random(pers_x-100, pers_x+100), random(240, 260))
        point += 1
        println("point : "+point)
    }
    if (kaktus2.position.y <= -300) {
        kaktus2.setPosition(random(pers_x-100, pers_x+100), random(240, 260))
        point += 1
        println("point : "+point)
    }
    if (kaktus3.position.x >= 300) {
        kaktus3.setPosition(random(-240, -260), random(pers_y-100, pers_y+100))
        point += 1
        println("point : "+point)
    }
    if (kaktus4.position.x >= 300) {
        kaktus4.setPosition(random(-240, -260), random(pers_y-100, pers_y+100))
        point += 1
        println("point : "+point)
    }
    if (kaktus5.position.y >= 300) {
        kaktus5.setPosition(random(pers_x-100, pers_x+100), random(-240, -260))
        point += 1
        println("point : "+point)
    }
    if (kaktus6.position.y >= 300) {
        kaktus6.setPosition(random(pers_x-100, pers_x+100), random(-240, -260))
        point += 1
        println("point : "+point)
    }
    if (kaktus7.position.x <= -300) {
        kaktus7.setPosition(random(260, 240), random(pers_y-100, pers_y+100))
        point += 1
        println("point : "+point)
    }
    if (kaktus8.position.x <= -300) {
        kaktus7.setPosition(random(260, 240), random(pers_y-100, pers_y+100))
        point += 11
        println("point : "+point)
    }
    
    if (ded) {
        kaktus1.offset(0, -z)
        kaktus2.offset(0, -z)
        kaktus3.offset(z, 0)
        kaktus4.offset(z, 0)
        kaktus5.offset(0, z)
        kaktus6.offset(0, z)
        kaktus7.offset(-z, 0) 
        kaktus8.offset(-z, 0)
    }
    
    if (pers.intersects(zel1)) {
        zel1_funtion()
    }
    
    if (TOPpoint < point) {
        TOPpoint = point
    }
    if (point >= 64 && ach1) {
        println("ДОСТИЖЕНИЕ: Стак Процентов (соберите 64 бала)")
        ach1 = false
    }
    if (pers.intersects(kaktus1) || pers.intersects(kaktus2) || pers.intersects(kaktus3) || pers.intersects(kaktus4) || pers.intersects(kaktus5) || pers.intersects(kaktus6) || pers.intersects(kaktus7) || pers.intersects(kaktus8)) {
        hp -= 1
        if (hp < 1) {
            println("==-- --==")
            point = 0
            hp = 3
            if (ach2) {
                println("ДОСТИЖЕНИЕ: Не Большая Царапена (умрите)")
                ach2 = false
            }
        }
        else {
            println("Ослалось "+hp+":hp")
        }
        
        restart()
    }
}
/* 
    ЧТО МОЖНО ОЖИДАТЬ В СЛЕДУЮЩЕЙ ВЕРСИИ
1)
    ДОБАВЛЕННО В ЭТОЙ ВЕРСИИ
1) Новый левел (ночь)
    а) персонаж
    б) кактус
    в) лого
    г) фон
    д) зелье
2) Новая ачивка (Мирный Рестарт)
*/
