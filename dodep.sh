#!/bin/bash

gradle -PenvProp=chpcServer.properties clean installDist bootRepackage copyResourcesToInstallDir copyToDeploy deleteDeployZip deployZip deployToCHPCServer
