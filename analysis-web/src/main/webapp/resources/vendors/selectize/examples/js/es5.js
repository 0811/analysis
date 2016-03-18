(function (o) {
    "function" == typeof define ? define(o) : "function" == typeof YUI ? YUI.add("es5", o) : o()
})(function () {
    function o() {
    }

    function v(a) {
        a = +a;
        a !== a ? a = 0 : 0 !== a && (a !== 1 / 0 && a !== -(1 / 0)) && (a = (0 < a || -1) * Math.floor(Math.abs(a)));
        return a
    }

    function s(a) {
        var b = typeof a;
        return null === a || "undefined" === b || "boolean" === b || "number" === b || "string" === b
    }

    Function.prototype.bind || (Function.prototype.bind = function (a) {
        var b = this;
        if ("function" != typeof b)throw new TypeError("Function.prototype.bind called on incompatible " + b);
        var d = q.call(arguments, 1), c = function () {
            if (this instanceof c) {
                var e = b.apply(this, d.concat(q.call(arguments)));
                return Object(e) === e ? e : this
            }
            return b.apply(a, d.concat(q.call(arguments)))
        };
        b.prototype && (o.prototype = b.prototype, c.prototype = new o, o.prototype = null);
        return c
    });
    var k = Function.prototype.call, p = Object.prototype, q = Array.prototype.slice, h = k.bind(p.toString), t = k.bind(p.hasOwnProperty);
    t(p, "__defineGetter__") && (k.bind(p.__defineGetter__), k.bind(p.__defineSetter__), k.bind(p.__lookupGetter__), k.bind(p.__lookupSetter__));
    if (2 != [1, 2].splice(0).length) {
        var y = Array.prototype.splice;
        Array.prototype.splice = function (a, b) {
            return arguments.length ? y.apply(this, [a === void 0 ? 0 : a, b === void 0 ? this.length - a : b].concat(q.call(arguments, 2))) : []
        }
    }
    if (1 != [].unshift(0)) {
        var z = Array.prototype.unshift;
        Array.prototype.unshift = function () {
            z.apply(this, arguments);
            return this.length
        }
    }
    Array.isArray || (Array.isArray = function (a) {
        return h(a) == "[object Array]"
    });
    var k = Object("a"), l = "a" != k[0] || !(0 in k);
    Array.prototype.forEach || (Array.prototype.forEach =
        function (a, b) {
            var d = n(this), c = l && h(this) == "[object String]" ? this.split("") : d, e = -1, f = c.length >>> 0;
            if (h(a) != "[object Function]")throw new TypeError;
            for (; ++e < f;)e in c && a.call(b, c[e], e, d)
        });
    Array.prototype.map || (Array.prototype.map = function (a, b) {
        var d = n(this), c = l && h(this) == "[object String]" ? this.split("") : d, e = c.length >>> 0, f = Array(e);
        if (h(a) != "[object Function]")throw new TypeError(a + " is not a function");
        for (var g = 0; g < e; g++)g in c && (f[g] = a.call(b, c[g], g, d));
        return f
    });
    Array.prototype.filter || (Array.prototype.filter =
        function (a, b) {
            var d = n(this), c = l && h(this) == "[object String]" ? this.split("") : d, e = c.length >>> 0, f = [], g;
            if (h(a) != "[object Function]")throw new TypeError(a + " is not a function");
            for (var i = 0; i < e; i++)if (i in c) {
                g = c[i];
                a.call(b, g, i, d) && f.push(g)
            }
            return f
        });
    Array.prototype.every || (Array.prototype.every = function (a, b) {
        var d = n(this), c = l && h(this) == "[object String]" ? this.split("") : d, e = c.length >>> 0;
        if (h(a) != "[object Function]")throw new TypeError(a + " is not a function");
        for (var f = 0; f < e; f++)if (f in c && !a.call(b, c[f],
                f, d))return false;
        return true
    });
    Array.prototype.some || (Array.prototype.some = function (a, b) {
        var d = n(this), c = l && h(this) == "[object String]" ? this.split("") : d, e = c.length >>> 0;
        if (h(a) != "[object Function]")throw new TypeError(a + " is not a function");
        for (var f = 0; f < e; f++)if (f in c && a.call(b, c[f], f, d))return true;
        return false
    });
    Array.prototype.reduce || (Array.prototype.reduce = function (a) {
        var b = n(this), d = l && h(this) == "[object String]" ? this.split("") : b, c = d.length >>> 0;
        if (h(a) != "[object Function]")throw new TypeError(a +
        " is not a function");
        if (!c && arguments.length == 1)throw new TypeError("reduce of empty array with no initial value");
        var e = 0, f;
        if (arguments.length >= 2)f = arguments[1]; else {
            do {
                if (e in d) {
                    f = d[e++];
                    break
                }
                if (++e >= c)throw new TypeError("reduce of empty array with no initial value");
            } while (1)
        }
        for (; e < c; e++)e in d && (f = a.call(void 0, f, d[e], e, b));
        return f
    });
    Array.prototype.reduceRight || (Array.prototype.reduceRight = function (a) {
        var b = n(this), d = l && h(this) == "[object String]" ? this.split("") : b, c = d.length >>> 0;
        if (h(a) !=
            "[object Function]")throw new TypeError(a + " is not a function");
        if (!c && arguments.length == 1)throw new TypeError("reduceRight of empty array with no initial value");
        var e, c = c - 1;
        if (arguments.length >= 2)e = arguments[1]; else {
            do {
                if (c in d) {
                    e = d[c--];
                    break
                }
                if (--c < 0)throw new TypeError("reduceRight of empty array with no initial value");
            } while (1)
        }
        do c in this && (e = a.call(void 0, e, d[c], c, b)); while (c--);
        return e
    });
    if (!Array.prototype.indexOf || -1 != [0, 1].indexOf(1, 2))Array.prototype.indexOf = function (a) {
        var b = l &&
        h(this) == "[object String]" ? this.split("") : n(this), d = b.length >>> 0;
        if (!d)return -1;
        var c = 0;
        arguments.length > 1 && (c = v(arguments[1]));
        for (c = c >= 0 ? c : Math.max(0, d + c); c < d; c++)if (c in b && b[c] === a)return c;
        return -1
    };
    if (!Array.prototype.lastIndexOf || -1 != [0, 1].lastIndexOf(0, -3))Array.prototype.lastIndexOf = function (a) {
        var b = l && h(this) == "[object String]" ? this.split("") : n(this), d = b.length >>> 0;
        if (!d)return -1;
        var c = d - 1;
        arguments.length > 1 && (c = Math.min(c, v(arguments[1])));
        for (c = c >= 0 ? c : d - Math.abs(c); c >= 0; c--)if (c in b &&
            a === b[c])return c;
        return -1
    };
    if (!Object.keys) {
        var w = !0, x = "toString toLocaleString valueOf hasOwnProperty isPrototypeOf propertyIsEnumerable constructor".split(" "), A = x.length, r;
        for (r in{toString: null})w = !1;
        Object.keys = function (a) {
            if (typeof a != "object" && typeof a != "function" || a === null)throw new TypeError("Object.keys called on a non-object");
            var b = [], d;
            for (d in a)t(a, d) && b.push(d);
            if (w)for (d = 0; d < A; d++) {
                var c = x[d];
                t(a, c) && b.push(c)
            }
            return b
        }
    }
    if (!Date.prototype.toISOString || -1 === (new Date(-621987552E5)).toISOString().indexOf("-000001"))Date.prototype.toISOString =
        function () {
            var a, b, d, c;
            if (!isFinite(this))throw new RangeError("Date.prototype.toISOString called on non-finite value.");
            c = this.getUTCFullYear();
            a = this.getUTCMonth();
            c = c + Math.floor(a / 12);
            a = [(a % 12 + 12) % 12 + 1, this.getUTCDate(), this.getUTCHours(), this.getUTCMinutes(), this.getUTCSeconds()];
            c = (c < 0 ? "-" : c > 9999 ? "+" : "") + ("00000" + Math.abs(c)).slice(0 <= c && c <= 9999 ? -4 : -6);
            for (b = a.length; b--;) {
                d = a[b];
                d < 10 && (a[b] = "0" + d)
            }
            return c + "-" + a.slice(0, 2).join("-") + "T" + a.slice(2).join(":") + "." + ("000" + this.getUTCMilliseconds()).slice(-3) +
                "Z"
        };
    r = !1;
    try {
        r = Date.prototype.toJSON && null === (new Date(NaN)).toJSON() && -1 !== (new Date(-621987552E5)).toJSON().indexOf("-000001") && Date.prototype.toJSON.call({
            toISOString: function () {
                return true
            }
        })
    } catch (H) {
    }
    r || (Date.prototype.toJSON = function () {
        var a = Object(this), b;
        a:if (s(a))b = a; else {
            b = a.valueOf;
            if (typeof b === "function") {
                b = b.call(a);
                if (s(b))break a
            }
            b = a.toString;
            if (typeof b === "function") {
                b = b.call(a);
                if (s(b))break a
            }
            throw new TypeError;
        }
        if (typeof b === "number" && !isFinite(b))return null;
        b = a.toISOString;
        if (typeof b != "function")throw new TypeError("toISOString property is not callable");
        return b.call(a)
    });
    var g = Date, m = function (a, b, d, c, e, f, h) {
        var i = arguments.length;
        if (this instanceof g) {
            i = i == 1 && String(a) === a ? new g(m.parse(a)) : i >= 7 ? new g(a, b, d, c, e, f, h) : i >= 6 ? new g(a, b, d, c, e, f) : i >= 5 ? new g(a, b, d, c, e) : i >= 4 ? new g(a, b, d, c) : i >= 3 ? new g(a, b, d) : i >= 2 ? new g(a, b) : i >= 1 ? new g(a) : new g;
            i.constructor = m;
            return i
        }
        return g.apply(this, arguments)
    }, u = function (a, b) {
        var d = b > 1 ? 1 : 0;
        return B[b] + Math.floor((a - 1969 + d) / 4) - Math.floor((a -
            1901 + d) / 100) + Math.floor((a - 1601 + d) / 400) + 365 * (a - 1970)
    }, C = RegExp("^(\\d{4}|[+-]\\d{6})(?:-(\\d{2})(?:-(\\d{2})(?:T(\\d{2}):(\\d{2})(?::(\\d{2})(?:\\.(\\d{3}))?)?(Z|(?:([-+])(\\d{2}):(\\d{2})))?)?)?)?$"), B = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365], j;
    for (j in g)m[j] = g[j];
    m.now = g.now;
    m.UTC = g.UTC;
    m.prototype = g.prototype;
    m.prototype.constructor = m;
    m.parse = function (a) {
        var b = C.exec(a);
        if (b) {
            var d = Number(b[1]), c = Number(b[2] || 1) - 1, e = Number(b[3] || 1) - 1, f = Number(b[4] || 0), h = Number(b[5] || 0), i = Number(b[6] ||
            0), j = Number(b[7] || 0), m = !b[4] || b[8] ? 0 : Number(new g(1970, 0)), k = b[9] === "-" ? 1 : -1, l = Number(b[10] || 0), b = Number(b[11] || 0);
            if (f < (h > 0 || i > 0 || j > 0 ? 24 : 25) && h < 60 && i < 60 && j < 1E3 && c > -1 && c < 12 && l < 24 && b < 60 && e > -1 && e < u(d, c + 1) - u(d, c)) {
                d = ((u(d, c) + e) * 24 + f + l * k) * 60;
                d = ((d + h + b * k) * 60 + i) * 1E3 + j + m;
                if (-864E13 <= d && d <= 864E13)return d
            }
            return NaN
        }
        return g.parse.apply(this, arguments)
    };
    Date = m;
    Date.now || (Date.now = function () {
        return (new Date).getTime()
    });
    if ("0".split(void 0, 0).length) {
        var D = String.prototype.split;
        String.prototype.split = function (a,
                                           b) {
            return a === void 0 && b === 0 ? [] : D.apply(this, arguments)
        }
    }
    if ("".substr && "b" !== "0b".substr(-1)) {
        var E = String.prototype.substr;
        String.prototype.substr = function (a, b) {
            return E.call(this, a < 0 ? (a = this.length + a) < 0 ? 0 : a : a, b)
        }
    }
    j = "\t\n\x0B\f\r \u00a0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029\ufeff";
    if (!String.prototype.trim || j.trim()) {
        j = "[" + j + "]";
        var F = RegExp("^" + j + j + "*"), G = RegExp(j + j + "*$");
        String.prototype.trim = function () {
            if (this === void 0 || this ===
                null)throw new TypeError("can't convert " + this + " to object");
            return String(this).replace(F, "").replace(G, "")
        }
    }
    var n = function (a) {
        if (a == null)throw new TypeError("can't convert " + a + " to object");
        return Object(a)
    }
});