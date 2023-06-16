cleari
var karta1 = 600
var karta2 = 900
var karta = Picture.rect(karta1, karta2)
karta.draw()
karta.setPosition(-455, -300)
var f1 = false
var pers = Picture.image("pers.png")
pers.draw()
pers.scale(1.5)
pers.setPosition(5, -10)
//var pers_x = pers.position.x
//var pers_y = pers.position.y
var vrag1: Seq[Picture] = Seq()
var vrag: Seq[Picture] = Seq()
var s1: Seq[Picture] = Seq()
var k = 0
var hp = 100

animate {
    onKeyPress {
        case Kc.VK_A =>
        pers.offset(-5, 0)
        case Kc.VK_D =>
        pers.offset(5, 0)
        case Kc.VK_S =>
        pers.offset(0, -5)
        case Kc.VK_W =>
        pers.offset(0, 5)
        case Kc.VK_F =>
            f1 = true
    }
    onKeyRelease {
        case Kc.VK_F =>
            f1 = false
    
    }
    if(f1) {
        for (i <- 0 until 360 by 10) {
            var p = Picture.circle(5)
            p.draw()
            p.rotate(i)
            s1 = s1 :+ p
            p.setPosition(pers.position.x+25, pers.position.y+25)
        }
    }
    for (p <- s1) {
        p.translate(10, 0)
        if(p.intersects(karta)){
            p.erase()
        }
    }
    f1 = false
    if (pers.intersects(karta)) pers.setPosition(5, -10)
    if (k % 200 == 0 && k > 1) {
        for (i <- 1 to 3) {
            var Vrag1 = Picture.image("vrag1.png")
            Vrag1.draw()
            Vrag1.setPosition(random(-400, 400), random(-300, 300))
            vrag1 = vrag1 :+ Vrag1
        }
    }
    for (Vrag1 <- vrag1) {
        for (p <- s1) {
            if (Vrag1.intersects(p)) {
                var Vrag = Picture.image("vrag.png")
                Vrag.draw()
                Vrag.setPosition(Vrag1.position.x, Vrag1.position.y)
                vrag = vrag :+ Vrag
                Vrag1.erase()
                p.erase()
                
            }
        }
        if (Vrag1.intersects(karta)) {
            Vrag1.setPosition(random(-400, 400), random(-400, 400))
        }
    }
    for (Vrag <- vrag) {
        for (p <- s1) {
            if (Vrag.intersects(p)) {
                Vrag.erase()
                p.erase()
            }
        }
    }
    k += 1
}