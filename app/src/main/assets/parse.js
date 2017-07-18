function getR(){
	return Math.random().toString(10).substring(2);
}

function getS(a) {
            var c = function() {
                for (var d = 0,
                f = new Array(256), g = 0; 256 != g; ++g) {
                    d = g,
                    d = 1 & d ? -306674912 ^ d >>> 1 : d >>> 1,
                    d = 1 & d ? -306674912 ^ d >>> 1 : d >>> 1,
                    d = 1 & d ? -306674912 ^ d >>> 1 : d >>> 1,
                    d = 1 & d ? -306674912 ^ d >>> 1 : d >>> 1,
                    d = 1 & d ? -306674912 ^ d >>> 1 : d >>> 1,
                    d = 1 & d ? -306674912 ^ d >>> 1 : d >>> 1,
                    d = 1 & d ? -306674912 ^ d >>> 1 : d >>> 1,
                    d = 1 & d ? -306674912 ^ d >>> 1 : d >>> 1,
                    f[g] = d
                }
                return "undefined" != typeof Int32Array ? new Int32Array(f) : f;
            } (),
            b = function(g) {
                for (var j, k, h = -1,
                f = 0,
                d = g.length; f < d;) {
                    j = g.charCodeAt(f++),
                    j < 128 ? h = h >>> 8 ^ c[255 & (h ^ j)] : j < 2048 ? (h = h >>> 8 ^ c[255 & (h ^ (192 | j >> 6 & 31))], h = h >>> 8 ^ c[255 & (h ^ (128 | 63 & j))]) : j >= 55296 && j < 57344 ? (j = (1023 & j) + 64, k = 1023 & g.charCodeAt(f++), h = h >>> 8 ^ c[255 & (h ^ (240 | j >> 8 & 7))], h = h >>> 8 ^ c[255 & (h ^ (128 | j >> 2 & 63))], h = h >>> 8 ^ c[255 & (h ^ (128 | k >> 6 & 15 | (3 & j) << 4))], h = h >>> 8 ^ c[255 & (h ^ (128 | 63 & k))]) : (h = h >>> 8 ^ c[255 & (h ^ (224 | j >> 12 & 15))], h = h >>> 8 ^ c[255 & (h ^ (128 | j >> 6 & 63))], h = h >>> 8 ^ c[255 & (h ^ (128 | 63 & j))]);
                }
                return h ^ -1;
            };
            return b(a) >>> 0;
        }

function getParseParam(link){
	var r = getR();
	var s = getS(link + "@" + r)
	window.chaychan.onReceiveParams(r,s);
}