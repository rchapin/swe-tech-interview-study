#!/bin/bash

set -e
set -u

VIRT_ENV_PATH=~/.virtualenvs/python-algos
rm -rf $VIRT_ENV_PATH
python3.10 -mvenv $VIRT_ENV_PATH
