environments {
    local {
        ext.envProp = 'local.properties'
        ext.softwarePath = System.getProperty("user.home") + '/workspace/cointossx'
    }

    windows {
        ext.envProp = 'windows.properties'
        ext.softwarePath = '/Users/dharmeshsing/Documents/cointoss_shared'
    }

    wits {
        ext.envProp = 'witsServer.properties'
        ext.softwarePath = '/hydrahome/1133465/Simulation'
    }

    chpc {
        ext.envProp = 'chpcServer.properties'
        ext.softwarePath = '/home/amo-hellenbrand/workspace/cointossx'
    }
}
